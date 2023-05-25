package metropolis.xtractedEditor.view.editor

import java.util.*
import androidx.compose.runtime.Composable
import metropolis.xtractedEditor.controller.editor.EditorAction
import metropolis.xtractedEditor.model.CH
import metropolis.xtractedEditor.model.EditorState
import metropolis.xtractedEditor.model.UndoState
import metropolis.xtractedEditor.view.ActionIconStrip
import metropolis.xtractedEditor.view.AlignLeftRight
import metropolis.xtractedEditor.view.Toolbar

@Composable
fun EditorBar(state: EditorState<*>, undoState: UndoState, trigger : (EditorAction) -> Unit) {
    Toolbar {
        AlignLeftRight{
            ActionIconStrip(trigger,
                            listOf(
                                EditorAction.Save(state.changed && state.valid),
                                   EditorAction.Reload),

                            listOf(
                                EditorAction.Undo(undoState.undoAvailable),
                                   EditorAction.Redo(undoState.redoAvailable)),
                            listOf(EditorAction.Delete)
                           )

            ActionIconStrip(trigger,
                            listOf(
                                EditorAction.SetLocale(CH, state.locale != CH),
                                   EditorAction.SetLocale(Locale.ENGLISH, state.locale != Locale.ENGLISH))
                           )
        }
    }
}