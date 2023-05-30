package metropolis.metropolistool.controller

import metropolis.cityexplorer.controller.cityExplorerController
import metropolis.countryexplorer.controller.countryExplorerController
import metropolis.shareddata.City
import metropolis.shareddata.ControllerType
import metropolis.shareddata.Country
import metropolis.xtractedEditor.repository.CrudRepository
import metropolis.xtractedEditor.controller.ControllerBase
import metropolis.xtractedExplorer.repository.LazyRepository

class MetropolisController(
    val cityRepository: LazyRepository<City>,
    val countryRepository: LazyRepository<Country>,
    val cityCrudRepository: CrudRepository<City>,
    val countryCrudRepository: CrudRepository<Country>
) : ControllerBase<MetropolisState, MetropolisAction>(
    MetropolisState(
        title = "Metropolis Demo",
        activeController = cityExplorerController(cityRepository, {}, {}), //todo?
        controllerType = ControllerType.CITY_EXPLORER
    )
) {

    private var cityExplorerController = cityExplorerController(repository = cityRepository, onCreate = { }, onSelected = { }) //todo?
    private var countryExplorerController = countryExplorerController(countryRepository)
    override fun executeAction(action: MetropolisAction): MetropolisState {
        return when (action) {
            is MetropolisAction.SelectCityExplorer -> {
              //  val editorController = cityEditorController(action.id, cityCrudRepository)
               state.copy(activeController = cityExplorerController,
                     controllerType = ControllerType.CITY_EXPLORER)
            }
            is MetropolisAction.SelectCountryExplorer -> {
                state.copy(activeController = countryExplorerController,
                    controllerType = ControllerType.COUNTRY_EXPLORER)
            }

//            is MetropolisAction.SelectCountryExplorer -> {
//                val editorController = countryEditorController(action.countryId, countryCrudRepository)
//                MetropolisState(state.title, editorController, ControllerType.COUNTRY_EDITOR)
//            }
//            is MetropolisAction.DeleteCity -> {
//                val cityEditorController = cityEditorController(action.cityId, cityCrudRepository)
//                MetropolisState(state.title, cityEditorController, ControllerType.CITY_EDITOR)
//            }

            else -> state
        }
    }


        private fun cityEditorController(id: Int, repository: CrudRepository<City>): ControllerBase<*, *> {
            return cityEditorController(id, repository)
        }

        private fun countryEditorController(id: Int, repository: CrudRepository<Country>): ControllerBase<*, *> {
            return countryEditorController(id, repository)
        }

        fun switchToCityExplorer() {
            state = state.copy(activeController = cityExplorerController, controllerType = ControllerType.CITY_EXPLORER)
        }

        fun switchToCountryExplorer() {
            state = state.copy(activeController = countryExplorerController, controllerType = ControllerType.COUNTRY_EXPLORER)
        }
    }
