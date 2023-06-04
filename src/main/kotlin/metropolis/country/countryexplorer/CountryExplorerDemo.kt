package metropolis.country.countryexplorer

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.country.countryexplorer.controller.countryExplorerController
import metropolis.country.countryexplorer.view.CountryExplorerWindow
import metropolis.sharedrepository.countryLazyRepository
import metropolis.xtracted.xtractedEditor.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url        = "/data/metropolisDB".urlFromResources()
    val countryExplorerController = countryExplorerController(countryLazyRepository(url), onCreate = { println("created") }, onSelected = { println("selected") })

    application {
        with(countryExplorerController){
            uiScope = rememberCoroutineScope()
            CountryExplorerWindow(state        = state,
                dataProvider = { getData(it) },
                idProvider   = { it.id },
                trigger      = { triggerAction(it) }
            )
        }
    }
}