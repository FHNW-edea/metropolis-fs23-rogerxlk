package metropolis.combined

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cityexplorer.controller.cityExplorerController
import metropolis.cityexplorer.view.CityExplorerWindow
import metropolis.countryexplorer.controller.countryExplorerController
import metropolis.repository.cityLazyRepository
import metropolis.repository.countryLazyRepository
import metropolis.xtractedEditor.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url = "/data/metropolisDB".urlFromResources()
    val cityExplorer = cityExplorerController(cityLazyRepository(url))
    val countryExplorer = countryExplorerController(countryLazyRepository(url))


    application {
        cityExplorer.initializeUiScope(rememberCoroutineScope())
        countryExplorer.initializeUiScope(rememberCoroutineScope())
        MetropolisWindow(cityState = cityExplorer.state,
            cityDataProvider = { cityExplorer.getData(it) },
            cityIdProvider = { it.id },
            countryState = countryExplorer.state,
            countryDataProvider = { countryExplorer.getData(it) },
            countryIdProvider = { it.id },
            cityTrigger = { cityExplorer.triggerAction(it) },
            countryTrigger = { countryExplorer.triggerAction(it) }
        )
    }
}