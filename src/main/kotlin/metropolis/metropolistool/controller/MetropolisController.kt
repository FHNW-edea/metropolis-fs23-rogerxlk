package metropolis.metropolistool.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cityeditor.controller.cityEditorController
import metropolis.cityexplorer.controller.cityExplorerController
import metropolis.countryeditor.controller.countryEditorController
import metropolis.countryexplorer.controller.countryExplorerController
import metropolis.shareddata.City
import metropolis.shareddata.Country
import metropolis.xtractedEditor.repository.CrudRepository
import metropolis.xtractedExplorer.repository.LazyRepository

class MetropolisController(
    val cityExplorerRepository: LazyRepository<City>,
    val cityEditorRepository: CrudRepository<City>,
    val countryExplorerRepository: LazyRepository<Country>,
    val countryEditorRepository: CrudRepository<Country>
) : Controller<MetropolisAction> {

    var state by mutableStateOf(
        MetropolisState(
            title = "METROPOLIS",
            cityExplorerController = cityExplorerController(cityExplorerRepository),
            cityEditorController = null,
            countryExplorerController = countryExplorerController(countryExplorerRepository),
            countryEditorController = null,
        )
    )

    override fun triggerAction(action: MetropolisAction) =
        when (action) {
//            is MetropolisAction.AddCity      -> addCity()
//            is MetropolisAction.RemoveCity   -> removeCity()
//            is MetropolisAction.UpdateCity   -> updateCity()
//            is MetropolisAction.AddCountry   -> addCountry()
//            is MetropolisAction.RemoveCountry-> removeCountry()
//            is MetropolisAction.UpdateCountry-> updateCountry()
            is MetropolisAction.AddItem<*> -> {
                addItem(action.item)
                state = state.copy(activeEditor = if (action.item is City) Editor.CITY_EDITOR else Editor.COUNTRY_EDITOR)
            }
            is MetropolisAction.RemoveItem<*> -> { removeItem(action.item) }
            is MetropolisAction.UpdateItem<*> -> {
                updateItem(action.item)
                state = state.copy(activeEditor = if (action.item is City) Editor.CITY_EDITOR else Editor.COUNTRY_EDITOR)
            }
        }

    private fun addItem(item: Any?) {
        when (item) {
            is City -> {
                with(state.cityExplorerController) {
                    openCityEditor(cityEditorRepository.createKey())
                    state.copy(
                        allIds = cityExplorerRepository.readFilteredIds(state.currentFilters, state.currentSort),
                        filteredCount = cityExplorerRepository.filteredCount(state.currentFilters),
                        totalCount = cityExplorerRepository.totalCount()
                    )
                }
            }

            is Country -> {
                with(state.countryExplorerController) {
                    openCountryEditor(countryEditorRepository.createKey())
                    state.copy(
                        allIds = countryExplorerRepository.readFilteredIds(state.currentFilters, state.currentSort),
                        filteredCount = countryExplorerRepository.filteredCount(state.currentFilters),
                        totalCount = countryExplorerRepository.totalCount()
                    )
                }
            }
        }
    }

    private fun removeItem(item: Any?) {
        when (item) {
            is City -> {
                with(state.cityExplorerController) {
                    cityEditorRepository.delete(item.id)
                    state.copy(
                        allIds = cityExplorerRepository.readFilteredIds(state.currentFilters, state.currentSort),
                        filteredCount = cityExplorerRepository.filteredCount(state.currentFilters),
                        totalCount = cityExplorerRepository.totalCount()
                    )
                }
            }

            is Country -> {
                with(state.countryExplorerController) {
                    countryEditorRepository.delete(item.id)
                    state.copy(
                        allIds = countryExplorerRepository.readFilteredIds(state.currentFilters, state.currentSort),
                        filteredCount = countryExplorerRepository.filteredCount(state.currentFilters),
                        totalCount = countryExplorerRepository.totalCount()
                    )
                }
            }
        }
    }

    private fun updateItem(item: Any?) {
        when (item) {
            is City -> {
                with(state.cityExplorerController) {
                    cityEditorRepository.update(item)
                    state.copy(
                        allIds = cityExplorerRepository.readFilteredIds(state.currentFilters, state.currentSort),
                        filteredCount = cityExplorerRepository.filteredCount(state.currentFilters),
                        totalCount = cityExplorerRepository.totalCount()
                    )
                }
            }

            is Country -> {
                with(state.countryExplorerController) {
                    countryEditorRepository.update(item)
                    state.copy(
                        allIds = countryExplorerRepository.readFilteredIds(state.currentFilters, state.currentSort),
                        filteredCount = countryExplorerRepository.filteredCount(state.currentFilters),
                        totalCount = countryExplorerRepository.totalCount()
                    )
                }
            }
        }
    }

    private fun openCityEditor(id: Int) {
        state = state.copy(
            cityEditorController = cityEditorController(id, cityEditorRepository),
        )
    }

    private fun closeCityEditor(closed: Boolean) {
        if (closed) {
            state = state.copy(cityExplorerController = cityExplorerController(cityExplorerRepository))
        }
    }

    private fun openCountryEditor(id: Int) {
        state = state.copy(
            countryEditorController = countryEditorController(id, countryEditorRepository),
        )
    }

    private fun closeCountryEditor(closed: Boolean) {
        if (closed) {
            state = state.copy(countryExplorerController = countryExplorerController(countryExplorerRepository))
        }
    }
}


    //    private fun addCity() {
//        val id = cityEditorRepository.createKey()
//        openCityEditor(id)
//    }
//
//    private fun removeCity() {
//        val selectedId = state.cityExplorerController.state.selectedId
//        if (selectedId != null) {
//            cityEditorRepository.delete(selectedId)
//            // TODO: recompose city explorer?
//        }
//    }
//
//    private fun updateCity() {
//        val selectedId = state.cityExplorerController.state.selectedId
//        if (selectedId != null) {
//            val currentCity = cityEditorRepository.read(selectedId)
//            if (currentCity != null) {
//                openCityEditor(currentCity.id)
//            }
//        }
//    }

//}
