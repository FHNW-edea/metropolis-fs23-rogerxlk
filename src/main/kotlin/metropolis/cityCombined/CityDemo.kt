package metropolis.cityCombined

import androidx.compose.ui.window.application
import metropolis.cityCombined.controller.CityController
import metropolis.cityCombined.view.CityWindow
import metropolis.cityeditor.controller.cityEditorController
import metropolis.sharedrepository.cityCrudRepository
import metropolis.sharedrepository.cityLazyRepository
import metropolis.xtractedEditor.repository.urlFromResources

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val cityExplorerRepository = cityLazyRepository(url)
    val cityEditorRepository = cityCrudRepository(url)

    val controller = CityController(cityExplorerRepository, cityEditorRepository)

    application {
        CityWindow(controller)
    }
}