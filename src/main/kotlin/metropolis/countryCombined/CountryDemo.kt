package metropolis.countryCombined

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.countryCombined.controller.CountryController
import metropolis.countryCombined.view.CountryWindow
import metropolis.sharedrepository.countryCrudRepository
import metropolis.sharedrepository.countryLazyRepository
import metropolis.xtracted.xtractedEditor.repository.urlFromResources

fun main() {
    val url = "/data/metropolisDB".urlFromResources()
    val countryExplorerRepository = countryLazyRepository(url)
    val countryEditorRepository = countryCrudRepository(url)

    val controller = CountryController(countryExplorerRepository, countryEditorRepository)

    controller.switchToOverview()
    application {
        controller.uiScope = rememberCoroutineScope()
        controller.state.activeController?.uiScope = controller.uiScope
        CountryWindow(controller)
    }
}