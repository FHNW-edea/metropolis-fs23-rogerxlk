package metropolis.cityCombined.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import metropolis.cityeditor.controller.cityEditorController
import metropolis.cityexplorer.controller.cityExplorerController
import metropolis.shareddata.City
import metropolis.shareddata.ControllerType
import metropolis.xtracted.IControllerBase
import metropolis.xtracted.xtractedEditor.repository.CrudRepository
import metropolis.xtracted.xtractedExplorer.controller.Action
import metropolis.xtracted.xtractedExplorer.repository.LazyRepository

class CityController(
    val repository: LazyRepository<City>,
    val crudRepository: CrudRepository<City>,
): IControllerBase<CityState, Action> {

    override var state by mutableStateOf(CityState(
        title = "City Demo",
        activeController = createExplorerController(),
        controllerType = ControllerType.CITY_EXPLORER
    ))

    private fun switchToEditor(city: City) {
        state = state.copy(activeController = createEditorController(city), controllerType = ControllerType.CITY_EDITOR)
    }

    private fun switchToNewEntry() {
        val newId = crudRepository.createKey()
        val newCity = City(id = newId, name = "New City")
        state = state.copy(activeController = createEditorController(newCity), controllerType = ControllerType.CITY_EDITOR)
    }

    private fun switchToOverview() {
        state = state.copy(activeController = createExplorerController(), controllerType = ControllerType.CITY_EXPLORER)
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

    private fun createEditorController(city: City): IControllerBase<*, *> =
        cityEditorController(
            id = city.id,
            repository = crudRepository,
            onSave = {
                crudRepository.update(city)
                switchToOverview()
            },
            onDelete = {
                crudRepository.delete(city.id)
                switchToOverview()
            },
        )

    override fun executeAction(action: Action): CityState {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }

    override fun handleNextAction(): Job {
        TODO("Not yet implemented")
    }

    override fun initializeUiScope(scope: CoroutineScope) {
        TODO("Not yet implemented")
    }

    override suspend fun handleAction(action: Action) {
        TODO("Not yet implemented")
    }

    override fun triggerAction(action: Action): Job {
        TODO("Not yet implemented")
    }
}
