package metropolis.xtractedExplorer.controller.lazyloading

import java.util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.focus.FocusRequester
import metropolis.xtractedExplorer.controller.ControllerBase
import metropolis.xtractedExplorer.controller.LRUCache
import metropolis.xtractedExplorer.controller.Scheduler
import metropolis.xtractedExplorer.data.Filter
import metropolis.xtractedExplorer.data.SortDirection
import metropolis.xtractedExplorer.data.SortDirective
import metropolis.xtractedExplorer.data.UNORDERED
import metropolis.xtractedExplorer.model.TableColumn
import metropolis.xtractedExplorer.model.TableState
import metropolis.xtractedExplorer.repository.LazyRepository


class LazyTableController<T>(title                  : String,
                             private val repository : LazyRepository<T>,
                             columns                : List<TableColumn<T, *>>,
                             private val defaultItem: T) :
        ControllerBase<TableState<T>, LazyTableAction>(initialState = TableState(title            = title,
                                                                                 triggerRecompose = false,
                                                                                 allIds           = repository.readFilteredIds(emptyList(), SortDirective(null)),
                                                                                 selectedId       = null,
                                                                                 lazyListState    = LazyListState(),
                                                                                 focusRequester   = FocusRequester(),
                                                                                 currentFilters   = emptyList(),
                                                                                 currentSort      = UNORDERED,
                                                                                 filteredCount    = repository.filteredCount(emptyList()),
                                                                                 totalCount       = repository.totalCount(),
                                                                                 columns          = columns
                                                                                )
                                                         ) {
    // filtern erst nach einer gewissen 'Ruhezeit'
    private val filterScheduler = Scheduler(200)

    private val maxNumberOfEntriesInCache = 100
    private val cache = Collections.synchronizedMap(LRUCache<Int, T>(maxNumberOfEntriesInCache))

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getData(id: Int): T =
        cache.computeIfAbsent(id) {
            val deferred = ioScope.async {
                delay(60) // nur zur Simulation eines langsamen DB-Zugriffs
                repository.read(id)!!
            }
            deferred.invokeOnCompletion {
                val loadedItem = deferred.getCompleted()
                cache[id] = loadedItem
                if (isVisible(id)) {  //es kann sein, dass mittlerweile schon an eine andere Stelle gescrollt wurde
                    recompose()
                }
            }

            defaultItem
        }


    override fun executeAction(action: LazyTableAction) : TableState<T> =
        when (action) {
            is LazyTableAction.Select             -> changeSelection(action.id)
            is LazyTableAction.SelectNext         -> selectNext()
            is LazyTableAction.SelectPrevious     -> selectPrevious()
            is LazyTableAction.SetFilter<*>       -> setFilter(action.column as TableColumn<T, *>, action.filter)
            is LazyTableAction.ToggleSortOrder<*> -> toggleSortOrder(action.column as TableColumn<T, *>)
        }

    private fun changeSelection(id: Int) =
         state.copy(selectedId = id)

    private  fun selectNext() =
        with(state) {
            focusRequester.requestFocus()
            val nextIdx = (allIds.indexOf(selectedId ?: -1) + 1).coerceAtMost(filteredCount - 1)
            if (nextIdx >= lazyListState.firstVisibleItemIndex + lazyListState.layoutInfo.visibleItemsInfo.size - 2) {
                scrollToIdx(lazyListState.firstVisibleItemIndex + 1)
            }
            changeSelection(allIds[nextIdx])
        }

    private fun selectPrevious() =
        with(state) {
            focusRequester.requestFocus()
            val nextIdx = (allIds.indexOf(selectedId ?: 1) - 1).coerceAtLeast(0)
            if (nextIdx < lazyListState.firstVisibleItemIndex) {
                scrollToIdx(lazyListState.firstVisibleItemIndex - 1)
            }
            changeSelection(allIds[nextIdx])
        }

    private fun setFilter(column: TableColumn<T, *>, filter: String) : TableState<T> {
        column.filterAsText = filter
        filterScheduler.scheduleTask {
            if (column.validFilterDescription()) {
                val allIds = repository.readFilteredIds(filters       = createFilterList(),
                                                        sortDirective = state.currentSort)
                scrollToIdx(0)
                state = state.copy(allIds        = allIds,
                                   filteredCount = allIds.size)
            }
        }
        return state
    }

    private fun toggleSortOrder(column: TableColumn<T, *>): TableState<T> {
        val currentSortDirective = state.currentSort
        val nextSortDirective =
            when {
                null == column.dbColumnExplorer                        -> UNORDERED

                currentSortDirective.column == column.dbColumnExplorer -> if (currentSortDirective.direction == SortDirection.ASC) {
                                                                    SortDirective(currentSortDirective.column, SortDirection.DESC)
                                                                  } else {
                                                                      UNORDERED
                                                                  }
                UNORDERED == currentSortDirective ||
                currentSortDirective.column != column.dbColumnExplorer -> SortDirective(column.dbColumnExplorer, SortDirection.ASC)

                else                                           -> UNORDERED
            }

        scrollToIdx(0)

        return state.copy(currentSort = nextSortDirective,
                          allIds      = repository.readFilteredIds(createFilterList(), nextSortDirective))

    }

    // einige Hilfsfunktionen

    private fun createFilterList(): List<Filter<*>> =
        buildList {
            state.columns.forEach {
                if (it.dbColumnExplorer != null && it.filterAsText.isNotBlank() && it.validFilterDescription()) {
                    add(it.createFilter())
                }
            }
        }

    private fun scrollToIdx(idx: Int) =
        uiScope.launch {
            state.lazyListState.animateScrollToItem(idx, 0)
        }

    private fun isVisible(id: Int) : Boolean =
        with(state){
            val idx = allIds.indexOf(id)
            idx >= lazyListState.firstVisibleItemIndex &&
            idx < lazyListState.firstVisibleItemIndex + lazyListState.layoutInfo.visibleItemsInfo.size
        }

    private fun recompose() {
        state = state.copy(triggerRecompose = !state.triggerRecompose)
    }
}

