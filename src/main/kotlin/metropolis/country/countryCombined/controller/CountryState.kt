package metropolis.country.countryCombined.controller

import metropolis.shared.data.ControllerType
import metropolis.shared.xtracted.controller.ControllerBase

data class CountryState (
    val title: String,
    var activeController: ControllerBase<*, *>?,
    val controllerType: ControllerType
)