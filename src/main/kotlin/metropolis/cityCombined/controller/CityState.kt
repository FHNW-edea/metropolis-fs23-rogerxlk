package metropolis.cityCombined.controller

import metropolis.shareddata.ControllerType
import metropolis.xtracted.IControllerBase

data class CityState(
    val title: String,
    val activeController: IControllerBase<*, *>?,
    val controllerType: ControllerType
)