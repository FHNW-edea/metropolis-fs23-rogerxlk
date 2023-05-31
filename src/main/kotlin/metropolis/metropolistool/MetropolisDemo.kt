package metropolis.metropolistool

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cityCombined.controller.CityController
import metropolis.countryCombined.controller.CountryController
import metropolis.metropolistool.controller.MetropolisAction
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
    val logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME)
    logger.level = Level.INFO

    try {
        val url = "/data/metropolisDB".urlFromResources()
        val cityExplorerRepository = cityLazyRepository(url)
        val cityEditorRepository = cityCrudRepository(url)
        val countryExplorerRepository = countryLazyRepository(url)
        val countryEditorRepository = countryCrudRepository(url)

        val metropolisController = MetropolisController(
            cityController = CityController(cityExplorerRepository, cityEditorRepository),
            countryController = CountryController(countryExplorerRepository, countryEditorRepository),
        )

        metropolisController.executeAction(MetropolisAction.SelectCity)
        application {
            metropolisController.uiScope = rememberCoroutineScope()
            metropolisController.state.activeController?.uiScope = metropolisController.uiScope
            MetropolisWindow(metropolisController)
        }
    } catch (e: Exception) {
        logger.log(Level.SEVERE, "Error occurred: ", e)
    }
}

