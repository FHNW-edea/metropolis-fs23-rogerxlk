package metropolis.city.cityCombined

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.city.cityCombined.controller.CityController
import metropolis.city.cityCombined.view.CityWindow
import metropolis.shared.xtracted.repository.urlFromResources

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val cityExplorerRepository = metropolis.shared.repository.cityLazyRepository(url)
    val cityEditorRepository = metropolis.shared.repository.cityCrudRepository(url)

    val controller = CityController(cityExplorerRepository, cityEditorRepository)
    controller.switchToOverview()
    application {
        controller.uiScope = rememberCoroutineScope()
        controller.state.activeController?.uiScope = controller.uiScope
        CityWindow(controller)
    }
}