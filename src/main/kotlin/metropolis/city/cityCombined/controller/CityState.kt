package metropolis.city.cityCombined.controller

import metropolis.shared.data.ControllerType
import metropolis.shared.xtracted.controller.ControllerBase

data class CityState(
    val title: String,
    var activeController: ControllerBase<*, *>?,
    val controllerType: ControllerType
)