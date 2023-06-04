package metropolis.city.cityCombined.controller


import java.time.LocalDate
import metropolis.city.cityeditor.controller.cityEditorController
import metropolis.city.cityexplorer.controller.cityExplorerController
import metropolis.shared.data.City
import metropolis.shared.data.ControllerType
import metropolis.shared.repository.CityColumnEditor
import metropolis.shared.xtracted.controller.Action
import metropolis.shared.xtracted.controller.ControllerBase
import metropolis.shared.xtracted.controller.editor.EditorController
import metropolis.shared.xtracted.repository.editor.CrudRepository
import metropolis.shared.xtracted.repository.explorer.LazyRepository

class CityController(
    val repository: LazyRepository<City>,
    val crudRepository: CrudRepository<City>,
) : ControllerBase<CityState, Action>(
    CityState(
        title = "City Demo",
        activeController = null,
        controllerType = ControllerType.CITY_EXPLORER
    )
) {

    init {
        state.activeController = createExplorerController()
    }

    private fun switchToEditor(city: City) {
        state = state.copy(
            activeController = createEditorController(city),
            controllerType = ControllerType.CITY_EDITOR
        )
    }

    private fun switchToNewEntry() {
        val newId = crudRepository.createKey(
            """
            |       (${CityColumnEditor.NAME}, ${CityColumnEditor.LONGITUDE}, ${CityColumnEditor.LATITUDE}, ${CityColumnEditor.FEATURE_CLASS}, ${CityColumnEditor.FEATURE_CODE}, ${CityColumnEditor.COUNTRY_CODE}, ${CityColumnEditor.POPULATION}, ${CityColumnEditor.DEM}, ${CityColumnEditor.TIMEZONE}, ${CityColumnEditor.MODIFICATION_DATE}) 
            |VALUES ('',                       0.0,                         0.0,                   '',                               '',                            '',                             0,                              0.0,                     '',             '${LocalDate.now()}')""".trimMargin()
        )


        state = state.copy(
            activeController = createEditorController(crudRepository.read(newId)!!),
            controllerType = ControllerType.CITY_EDITOR
        )
    }

    fun switchToOverview() {
        state = state.copy(
            activeController = createExplorerController(),
            controllerType = ControllerType.CITY_EXPLORER
        )
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
                switchToOverview()
            },
            onDelete = {
                switchToOverview()
            },
        )
    }

    override fun executeAction(action: Action): CityState {
        TODO("Not yet implemented")
    }

}
