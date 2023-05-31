package metropolis.cityCombined

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cityCombined.controller.CityController
import metropolis.cityCombined.view.CityWindow
import metropolis.sharedrepository.cityCrudRepository
import metropolis.sharedrepository.cityLazyRepository
import metropolis.xtracted.xtractedEditor.repository.urlFromResources

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val cityExplorerRepository = cityLazyRepository(url)
    val cityEditorRepository = cityCrudRepository(url)

    val controller = CityController(cityExplorerRepository, cityEditorRepository)
    controller.switchToOverview()
    application {
        controller.uiScope = rememberCoroutineScope()
        controller.state.activeController?.uiScope = controller.uiScope
        CityWindow(controller)
    }
}