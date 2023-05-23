package metropolis.xtractedExplorer.model

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.focus.FocusRequester
import metropolis.xtractedExplorer.data.Filter
import metropolis.xtractedExplorer.data.SortDirective

data class TableState<T> (val title           : String,
                          val allIds          : List<Int>,
                          val columns         : List<TableColumn<T, *>>,
                          val selectedId      : Int?,
                          val currentSort     : SortDirective,
                          val currentFilters  : List<Filter<*>>,
                          val filteredCount   : Int,
                          val totalCount      : Int,
                          val lazyListState   : LazyListState,
                          val focusRequester  : FocusRequester,
                          val triggerRecompose: Boolean
)