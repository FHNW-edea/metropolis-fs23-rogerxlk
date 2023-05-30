package metropolis.cityexplorer

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cityexplorer.controller.cityExplorerController
import metropolis.cityexplorer.view.CityExplorerWindow
import metropolis.sharedrepository.cityLazyRepository
import metropolis.xtractedEditor.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url        = "/data/metropolisDB".urlFromResources()
    val cityExplorerController = cityExplorerController(
        repository = cityLazyRepository(url),
        onCreate = {},
        onSelected = { }
    )

    application {
        with(cityExplorerController){
            initializeUiScope(rememberCoroutineScope())
            CityExplorerWindow(state        = state,
                dataProvider = { getData(it) },
                idProvider   = { it.id },
                trigger      = { triggerAction(it) }
            )
        }
    }
}