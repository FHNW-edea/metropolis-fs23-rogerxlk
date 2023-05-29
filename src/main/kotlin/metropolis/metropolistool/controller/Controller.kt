package metropolis.metropolistool.controller

import metropolis.xtractedEditor.controller.Action

interface Controller<A : Action> {
    fun triggerAction(action: A)
}