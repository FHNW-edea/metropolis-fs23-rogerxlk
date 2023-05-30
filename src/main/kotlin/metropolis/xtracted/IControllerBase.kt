package metropolis.xtracted

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import metropolis.xtracted.xtractedExplorer.controller.Action

interface IControllerBase<S, A: Action> {

    var state: S
    fun executeAction(action: A): S
    fun triggerAction(action: A): Job
    fun close()

    // Unique functionalities, declare methods without body
    fun handleNextAction(): Job  // for first ControllerBase
    suspend fun handleAction(action: A)  // for second ControllerBase
    fun initializeUiScope(scope: CoroutineScope)  // for second ControllerBase
}

