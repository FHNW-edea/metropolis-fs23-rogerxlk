package metropolis.metropolis.controller

import metropolis.shared.data.ControllerType
import metropolis.shared.xtracted.controller.ControllerBase

data class MetropolisState(
        val title: String,
        val activeController: ControllerBase<*, *>?,
        val controllerType: ControllerType
)
