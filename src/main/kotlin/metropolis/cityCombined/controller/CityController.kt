package metropolis.cityCombined.controller


import java.time.LocalDate
import metropolis.cityeditor.controller.cityEditorController
import metropolis.cityexplorer.controller.cityExplorerController
import metropolis.shareddata.City
import metropolis.shareddata.ControllerType
import metropolis.sharedrepository.CityColumnEditor
import metropolis.xtracted.xtractedEditor.controller.ControllerBase
import metropolis.xtracted.xtractedEditor.controller.editor.EditorController
import metropolis.xtracted.xtractedEditor.repository.CrudRepository
import metropolis.xtracted.xtractedExplorer.controller.Action
import metropolis.xtracted.xtractedExplorer.repository.LazyRepository

class CityController(
    val repository: LazyRepository<City>,
    val crudRepository: CrudRepository<City>,
): ControllerBase<CityState, Action> (CityState(
        title = "City Demo",
        activeController = null,
        controllerType = ControllerType.CITY_EXPLORER
                                               )) {


    private fun switchToEditor(city: City) {
        state = state.copy(activeController = createEditorController(city),
                           controllerType = ControllerType.CITY_EDITOR)
    }

    private fun switchToNewEntry() {
        val newId = crudRepository.createKey("""
            |       (${CityColumnEditor.NAME}, ${CityColumnEditor.LONGITUDE}, ${CityColumnEditor.LATITUDE}, ${CityColumnEditor.FEATURE_CLASS}, ${CityColumnEditor.FEATURE_CODE}, ${CityColumnEditor.COUNTRY_CODE}, ${CityColumnEditor.POPULATION}, ${CityColumnEditor.DEM}, ${CityColumnEditor.TIMEZONE}, ${CityColumnEditor.MODIFICATION_DATE}) 
            |VALUES ('',                       0.0,                         0.0,                   '',                               '',                            '',                             0,                              0.0,                     '',             '${LocalDate.now()}')""".trimMargin())


        state = state.copy(activeController = createEditorController(crudRepository.read(newId)!!),
                           controllerType = ControllerType.CITY_EDITOR)
    }

    fun switchToOverview() {
        state = state.copy(activeController = createExplorerController(),
                           controllerType = ControllerType.CITY_EXPLORER)
    }

    private fun createExplorerController() =
        cityExplorerController(
            repository = repository,
            onCreate = { switchToNewEntry() },
            onSelected = {
                val selectedCity = repository.read(it)
                if (selectedCity != null) {
                    switchToEditor(selectedCity)
                }
            }
        )

    private fun createEditorController(city: City): EditorController<City> {
        return cityEditorController(
                id = city.id,
                repository = crudRepository,
                onSave = {
//                    crudRepository.update(city)
                    switchToOverview()
                },
                onDelete = {
//                    crudRepository.delete(city.id)
                    switchToOverview()
                },
                                   )
    }

    override fun executeAction(action: Action): CityState {
        TODO("Not yet implemented")
    }

}
