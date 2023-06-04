package metropolis.city.cityCombined.controller

import metropolis.shareddata.ControllerType
import metropolis.xtracted.xtractedEditor.controller.ControllerBase

data class CityState(
    val title: String,
    var activeController: ControllerBase<*, *>?,
    val controllerType: ControllerType
)