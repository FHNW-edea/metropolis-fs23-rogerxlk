package metropolis.city.cityeditor

import androidx.compose.ui.window.application
import metropolis.city.cityeditor.controller.cityEditorController
import metropolis.city.cityeditor.view.CityEditorWindow
import metropolis.shared.xtracted.repository.urlFromResources

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val repository = metropolis.shared.repository.cityCrudRepository(url)
    val cityId = 782270

    val controller = cityEditorController(cityId, repository, onSave = { println("saved") }, onDelete = { println("deleted") })

    application {
        CityEditorWindow(state = controller.state,
            undoState = controller.undoState,
            trigger = { controller.triggerAction(it) })
    }
}