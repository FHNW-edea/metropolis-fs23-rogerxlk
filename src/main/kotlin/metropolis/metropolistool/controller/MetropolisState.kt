package metropolis.metropolistool.controller

import metropolis.shareddata.ControllerType
import metropolis.xtractedExplorer.controller.ControllerBase

data class MetropolisState(
    val title: String,
    val activeController: ControllerBase<*,*>?,
    val controllerType: ControllerType
)
