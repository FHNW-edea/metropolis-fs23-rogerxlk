package metropolis.xtracted.xtractedEditor.model

data class UndoState(val undoAvailable: Boolean = false,
                     val redoAvailable: Boolean = false)