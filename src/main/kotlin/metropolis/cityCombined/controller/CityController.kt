package metropolis.cityCombined.controller

import metropolis.cityeditor.controller.cityEditorController
import metropolis.cityexplorer.controller.cityExplorerController
import metropolis.shareddata.City
import metropolis.shareddata.ControllerType
import metropolis.xtractedEditor.controller.Action
import metropolis.xtractedEditor.controller.ControllerBase
import metropolis.xtractedEditor.repository.CrudRepository
import metropolis.xtractedExplorer.repository.LazyRepository

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
        state = state.copy(activeController = createExplorerController())
    }

    private fun switchToEditor(id: Int) {
        state = state.copy(activeController = createEditorController(id), controllerType = ControllerType.CITY_EDITOR)
    }

    private fun switchToNewEntry() {
        val newEntry = crudRepository.createKey()
        switchToEditor(newEntry)
    }

    private fun switchToOverview() {
        state = state.copy(activeController = createExplorerController())
    }

    private fun createExplorerController() =
        cityExplorerController(
            repository = repository,
            onCreate = { switchToNewEntry() },
            onSelected = { switchToEditor(it) }
        )

    private fun createEditorController(item: City): ControllerBase<*, *> =
        cityEditorController(
            id = item.id,
            repository = crudRepository,
            onSaved = {
                switchToOverview()
                crudRepository.update(item)
            },
        )

    override fun executeAction(action: Action): CityState {
        TODO("Not yet implemented")
    }
}
