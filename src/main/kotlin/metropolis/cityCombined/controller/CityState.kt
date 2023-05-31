package metropolis.cityCombined.controller

import metropolis.shareddata.ControllerType
import metropolis.xtracted.xtractedEditor.controller.ControllerBase

data class CityState(
        val title: String,
        val activeController: ControllerBase<*, *>?,
        val controllerType: ControllerType
)