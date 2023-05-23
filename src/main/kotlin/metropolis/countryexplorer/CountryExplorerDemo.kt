package metropolis.countryexplorer

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.countryexplorer.controller.countryExplorerController
import metropolis.countryexplorer.view.CountryExplorerWindow
import metropolis.repository.countryLazyRepository
import metropolis.xtractedEditor.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url        = "/data/metropolisDB".urlFromResources()
    val countryExplorerController = countryExplorerController(countryLazyRepository(url))

    application {
        with(countryExplorerController){
            initializeUiScope(rememberCoroutineScope())
            CountryExplorerWindow(state        = state,
                dataProvider = { getData(it) },
                idProvider   = { it.id },
                trigger      = { triggerAction(it) }
            )
        }
    }
}