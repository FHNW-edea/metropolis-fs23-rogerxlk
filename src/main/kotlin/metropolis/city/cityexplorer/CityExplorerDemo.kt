package metropolis.city.cityexplorer

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.city.cityexplorer.controller.cityExplorerController
import metropolis.city.cityexplorer.view.CityExplorerWindow
import metropolis.shared.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url        = "/data/metropolisDB".urlFromResources()
    val cityExplorerController = cityExplorerController(
        repository = metropolis.shared.repository.cityLazyRepository(url),
        onCreate = {},
        onSelected = { }
    )

    application {
        with(cityExplorerController){
            uiScope = rememberCoroutineScope()
            CityExplorerWindow(state        = state,
                dataProvider = { getData(it) },
                idProvider   = { it.id },
                trigger      = { triggerAction(it) }
            )
        }
    }
}