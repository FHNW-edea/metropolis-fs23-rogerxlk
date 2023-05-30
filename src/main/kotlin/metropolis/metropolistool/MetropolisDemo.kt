package metropolis.metropolistool

import androidx.compose.ui.window.application
import metropolis.metropolistool.controller.MetropolisController
import metropolis.metropolistool.view.MetropolisWindow
import metropolis.sharedrepository.cityCrudRepository
import metropolis.sharedrepository.cityLazyRepository
import metropolis.sharedrepository.countryCrudRepository
import metropolis.sharedrepository.countryLazyRepository
import metropolis.xtracted.xtractedEditor.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url = "/data/metropolisDB".urlFromResources()
    val cityExplorerRepository = cityLazyRepository(url)
    val cityEditorRepository = cityCrudRepository(url)
    val countryExplorerRepository = countryLazyRepository(url)
    val countryEditorRepository = countryCrudRepository(url)

    val metropolisController = MetropolisController(
        cityExplorerRepository,
        countryExplorerRepository,
        cityEditorRepository,
        countryEditorRepository,
    )

    application {
        MetropolisWindow(metropolisController)
    }
}
