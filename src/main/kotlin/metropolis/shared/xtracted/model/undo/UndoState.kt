package metropolis.shared.xtracted.model.undo

data class UndoState(val undoAvailable: Boolean = false,
                     val redoAvailable: Boolean = false)