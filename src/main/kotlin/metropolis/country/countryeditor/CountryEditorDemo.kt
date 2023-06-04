package metropolis.country.countryeditor

import androidx.compose.ui.window.application
import metropolis.country.countryeditor.controller.countryEditorController
import metropolis.country.countryeditor.view.CountryEditorWindow
import metropolis.sharedrepository.countryCrudRepository
import metropolis.xtracted.xtractedEditor.repository.urlFromResources

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val repository = countryCrudRepository(url)
    val countryId = 100

    val controller = countryEditorController(countryId, repository, onSave = { println("saved") }, onDelete = { println("deleted") })

    application {
        CountryEditorWindow(state = controller.state,
            undoState = controller.undoState,
            trigger = { controller.triggerAction(it) })
    }
}