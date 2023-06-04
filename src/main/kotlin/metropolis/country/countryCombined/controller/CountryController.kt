package metropolis.country.countryCombined.controller

import metropolis.country.countryeditor.controller.countryEditorController
import metropolis.country.countryexplorer.controller.countryExplorerController
import metropolis.shared.data.ControllerType
import metropolis.shared.data.Country
import metropolis.shared.repository.CountryColumnEditor
import metropolis.shared.xtracted.controller.Action
import metropolis.shared.xtracted.controller.ControllerBase
import metropolis.shared.xtracted.controller.editor.EditorController
import metropolis.shared.xtracted.repository.editor.CrudRepository
import metropolis.shared.xtracted.repository.explorer.LazyRepository

class CountryController(
    val repository: LazyRepository<Country>,
    val crudRepository: CrudRepository<Country>,
) : ControllerBase<CountryState, Action>(
    CountryState(
        title = "Country Demo",
        activeController = null,
        controllerType = ControllerType.COUNTRY_EXPLORER
    )
) {

    init {
        state.activeController = createExplorerController()
    }

    private fun switchToEditor(country: Country) {
        state = state.copy(
            activeController = createEditorController(country),
            controllerType = ControllerType.COUNTRY_EDITOR
        )
    }

    private fun switchToNewEntry() {
        val newId = crudRepository.createKey(
            """
    |(${CountryColumnEditor.NAME}, ${CountryColumnEditor.ISO_ALPHA2}, ${CountryColumnEditor.ISO_ALPHA3}, ${CountryColumnEditor.AREA_IN_SQKM}, ${CountryColumnEditor.POPULATION}, ${CountryColumnEditor.CONTINENT}, ${CountryColumnEditor.GEONAME_ID}) 
    |VALUES ('', '', '', 0.0, 0, '', '')""".trimMargin()
        )


        state = state.copy(
            activeController = createEditorController(crudRepository.read(newId)!!),
            controllerType = ControllerType.COUNTRY_EDITOR
        )
    }

    fun switchToOverview() {
        state = state.copy(
            activeController = createExplorerController(),
            controllerType = ControllerType.COUNTRY_EXPLORER
        )
    }

    private fun createExplorerController() =
        countryExplorerController(
            repository = repository,
            onCreate = { switchToNewEntry() },
            onSelected = {
                val selectedCountry = repository.read(it)
                if (selectedCountry != null) {
                    switchToEditor(selectedCountry)
                }
            }
        )

    private fun createEditorController(country: Country): EditorController<Country> {
        return countryEditorController(
            id = country.id,
            repository = crudRepository,
            onSave = {
                switchToOverview()
            },
            onDelete = {
                switchToOverview()
            },
        )
    }

    override fun executeAction(action: Action): CountryState {
        TODO("Not yet implemented")
    }

}