package metropolis.countryCombined.controller

import metropolis.shareddata.ControllerType
import metropolis.xtracted.xtractedEditor.controller.ControllerBase

data class CountryState (
    val title: String,
    var activeController: ControllerBase<*, *>?,
    val controllerType: ControllerType
)