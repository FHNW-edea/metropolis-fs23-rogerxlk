package metropolis.hello

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.hello.controller.cityExplorerController
import metropolis.hello.controller.countryExplorerController
import metropolis.hello.view.CityExplorerWindow
import metropolis.hello.view.CountryExplorerWindow
import metropolis.repository.cityLazyRepository
import metropolis.repository.countryLazyRepository
import metropolis.xtractedEditor.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url        = "/data/metropolisDB".urlFromResources()
    val countryExplorerController = countryExplorerController(countryLazyRepository(url))
    val cityExplorerController = cityExplorerController(cityLazyRepository(url))

//    application {
//        with(countryExplorerController){
//            initializeUiScope(rememberCoroutineScope())
//            CountryExplorerWindow(state        = state,
//                dataProvider = { getData(it) },
//                idProvider   = { it.id },
//                trigger      = { triggerAction(it) }
//            )
//        }
//    }

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