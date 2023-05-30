package metropolis.metropolistool.controller

import metropolis.xtracted.IControllerBase
import metropolis.shareddata.ControllerType

data class MetropolisState(
    val title: String,
    val activeController: IControllerBase<*, *>?,
    val controllerType: ControllerType
)
