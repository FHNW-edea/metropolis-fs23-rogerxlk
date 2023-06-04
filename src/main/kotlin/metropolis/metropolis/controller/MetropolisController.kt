package metropolis.metropolis.controller

import metropolis.city.cityCombined.controller.CityController
import metropolis.country.countryCombined.controller.CountryController
import metropolis.shared.data.ControllerType
import metropolis.shared.xtracted.controller.ControllerBase

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

    //TODO: @Dieter Holz - Wieso funktioniert der Wechsel des Controllers via MetroplisController nicht? Irgendie wird der State nicht gesetzt.
    override fun executeAction(action: MetropolisAction): MetropolisState {
//        return when (action) {
//            is MetropolisAction.SelectCity -> {
//                state.copy(activeController = cityController,
//                     controllerType = ControllerType.CITY)
//            }
//            is MetropolisAction.SelectCountry -> {
//                state.copy(activeController = countryController,
//                    controllerType = ControllerType.COUNTRY)
//            }
//        }
        TODO("Not yet implemented")
    }

    fun switchToCityTable() {
        state = state.copy(
            activeController = cityController,
            controllerType = ControllerType.CITY
        )
    }

    fun switchToCountryTable() {
        state = state.copy(
            activeController = countryController,
            controllerType = ControllerType.COUNTRY
        )
    }
}
