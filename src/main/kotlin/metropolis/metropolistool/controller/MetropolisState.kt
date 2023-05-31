package metropolis.metropolistool.controller

import metropolis.shareddata.ControllerType
import metropolis.xtracted.xtractedEditor.controller.ControllerBase

data class MetropolisState(
        val title: String,
        val activeController: ControllerBase<*, *>?,
        val controllerType: ControllerType
)
