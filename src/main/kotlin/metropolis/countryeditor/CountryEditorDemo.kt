package metropolis.countryeditor

import androidx.compose.ui.window.application
import metropolis.xtractedEditor.repository.urlFromResources
import metropolis.countryeditor.controller.countryEditorController
import metropolis.countryeditor.view.CountryEditorWindow
import metropolis.sharedrepository.countryCrudRepository

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val repository = countryCrudRepository(url)
    val countryId = 100

    val controller = countryEditorController(countryId, repository)

    application {
        CountryEditorWindow(state = controller.state,
            undoState = controller.undoState,
            trigger = { controller.triggerAction(it) })
    }
}