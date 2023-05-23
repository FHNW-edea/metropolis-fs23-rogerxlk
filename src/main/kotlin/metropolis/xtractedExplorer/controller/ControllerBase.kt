package metropolis.xtractedExplorer.controller

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class ControllerBase<S, A: Action> (initialState: S){

    var state by mutableStateOf(initialState)
        protected set

    protected lateinit var uiScope: CoroutineScope

    protected val ioScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val actionChannel = Channel<A>(Channel.UNLIMITED)

    private var initialized = false

    fun initializeUiScope(scope : CoroutineScope){
        if(!initialized){
            uiScope = scope
            initialized = true

            uiScope.launch { // das Recompose sollte immer im uiScope passieren
                handleAction(actionChannel.receive())  //jede Action wird in einer neuen Coroutine abgearbeitet
            }
        }
    }

    /**
     * Wenn der Controller nicht mehr benötigt wird, muss der Channel geschlossen werden.
     */
    protected fun close() {
        actionChannel.cancel()
        actionChannel.close()
    }

    fun triggerAction(action: A) =
        ioScope.launch {
           actionChannel.send(action)  // die Action wird nur in den Channel gestellt. Dadurch wird die Reihenfolgetreue sichergestellt.
        }


    /**
     * führt die Action aus und liefert den neuen State zurück
     */
    abstract fun executeAction(action: A) : S

    private suspend fun handleAction(action: A) {
        if (action.enabled) {
            val deferredNewState = ioScope.async {
                executeAction(action)  // liefert den neuen TableState, der natürlich asynchron berechnet wird
            }

            val newState = deferredNewState.await()  //warten bis die Action ausgeführt wurde und den neuen State liefert
            if (state != newState) { //manche Action lassen den State unverändert
                state = newState
            }

            uiScope.launch {  //warten auf die nächste Action
                handleAction(actionChannel.receive())
            }
        }
        else {
            uiScope.launch {  //warten auf die nächste Action
                handleAction(actionChannel.receive())
            }
        }
    }
}