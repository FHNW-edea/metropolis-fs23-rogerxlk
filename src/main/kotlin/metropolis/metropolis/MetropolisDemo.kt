package metropolis.metropolis

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.city.cityCombined.controller.CityController
import metropolis.country.countryCombined.controller.CountryController
import metropolis.metropolis.controller.MetropolisController
import metropolis.metropolis.view.MetropolisWindow
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

        val cityController = CityController(cityExplorerRepository, cityEditorRepository)
        val countryController = CountryController(countryExplorerRepository, countryEditorRepository)

        val metropolisController = MetropolisController(
            cityController = cityController,
            countryController = countryController,
        )

        metropolisController.switchToCityTable()
        application {
            cityController.uiScope = rememberCoroutineScope()
            cityController.state.activeController?.uiScope = cityController.uiScope

            countryController.uiScope = rememberCoroutineScope()
            countryController.state.activeController?.uiScope = cityController.uiScope

            metropolisController.uiScope = rememberCoroutineScope()
            metropolisController.state.activeController?.uiScope = metropolisController.uiScope

            MetropolisWindow(metropolisController)
        }
    } catch (e: Exception) {
        logger.log(Level.SEVERE, "Error occurred: ", e)
    }
}

