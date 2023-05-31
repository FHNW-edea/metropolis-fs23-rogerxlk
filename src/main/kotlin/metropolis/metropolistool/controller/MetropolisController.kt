package metropolis.metropolistool.controller

import metropolis.cityCombined.controller.CityController
import metropolis.countryCombined.controller.CountryController
import metropolis.shareddata.ControllerType
import metropolis.xtracted.xtractedEditor.controller.ControllerBase

class MetropolisController(
    val cityController: CityController,
    val countryController: CountryController,
) : ControllerBase<MetropolisState, MetropolisAction>(
    MetropolisState(
        title = "Metropolis Demo",
        activeController = cityController,
        controllerType = ControllerType.CITY
    )
) {

    override fun executeAction(action: MetropolisAction): MetropolisState {
        return when (action) {
            is MetropolisAction.SelectCity -> {
               state.copy(activeController = cityController,
                     controllerType = ControllerType.CITY)
            }
            is MetropolisAction.SelectCountry -> {
                state.copy(activeController = countryController,
                    controllerType = ControllerType.COUNTRY)
            }
        }
    }
}
