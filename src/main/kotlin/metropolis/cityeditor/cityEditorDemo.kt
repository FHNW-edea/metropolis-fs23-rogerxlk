package metropolis.cityeditor

import androidx.compose.ui.window.application
import metropolis.cityeditor.controller.cityEditor
import metropolis.cityeditor.view.CityEditorWindow
import metropolis.xtractedEditor.repository.urlFromResources
import metropolis.sharedrepository.cityCrudRepository

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val repository = cityCrudRepository(url)
    val cityId = 782270

    val controller = cityEditor(cityId, repository)

    application {
        CityEditorWindow(state = controller.state,
            undoState = controller.undoState,
            trigger = { controller.triggerAction(it) })
    }
}