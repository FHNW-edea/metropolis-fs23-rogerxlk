package metropolis.cityeditor

import androidx.compose.ui.window.application
import metropolis.cityeditor.controller.cityEditorController
import metropolis.cityeditor.view.CityEditorWindow
import metropolis.sharedrepository.cityCrudRepository
import metropolis.xtracted.xtractedEditor.repository.urlFromResources

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val repository = cityCrudRepository(url)
    val cityId = 782270

    val controller = cityEditorController(cityId, repository, onSaved = { println("saved") })

    application {
        CityEditorWindow(state = controller.state,
            undoState = controller.undoState,
            trigger = { controller.triggerAction(it) })
    }
}