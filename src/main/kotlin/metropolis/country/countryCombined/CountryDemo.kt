package metropolis.country.countryCombined

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.country.countryCombined.controller.CountryController
import metropolis.country.countryCombined.view.CountryWindow
import metropolis.shared.repository.countryCrudRepository
import metropolis.shared.repository.countryLazyRepository
import metropolis.shared.xtracted.repository.urlFromResources

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