package metropolis.xtracted.xtractedEditor.view.editor

import java.util.*
import androidx.compose.runtime.Composable
import metropolis.xtracted.xtractedEditor.controller.editor.EditorAction
import metropolis.xtracted.xtractedEditor.model.CH
import metropolis.xtracted.xtractedEditor.model.EditorState
import metropolis.xtracted.xtractedEditor.model.UndoState
import metropolis.xtracted.xtractedEditor.view.ActionIconStrip
import metropolis.xtracted.xtractedEditor.view.AlignLeftRight
import metropolis.xtracted.xtractedEditor.view.Toolbar

@Composable
fun EditorBar(state: EditorState<*>, undoState: UndoState, trigger : (metropolis.xtracted.xtractedEditor.controller.editor.EditorAction) -> Unit) {
    Toolbar {
        AlignLeftRight{
            ActionIconStrip(trigger,
                            listOf(
                                metropolis.xtracted.xtractedEditor.controller.editor.EditorAction.Save(state.changed && state.valid),
                                   metropolis.xtracted.xtractedEditor.controller.editor.EditorAction.Reload),

                            listOf(
                                metropolis.xtracted.xtractedEditor.controller.editor.EditorAction.Undo(undoState.undoAvailable),
                                   metropolis.xtracted.xtractedEditor.controller.editor.EditorAction.Redo(undoState.redoAvailable)),
                            listOf(metropolis.xtracted.xtractedEditor.controller.editor.EditorAction.Delete)
                           )

            ActionIconStrip(trigger,
                            listOf(
                                metropolis.xtracted.xtractedEditor.controller.editor.EditorAction.SetLocale(CH, state.locale != CH),
                                   metropolis.xtracted.xtractedEditor.controller.editor.EditorAction.SetLocale(Locale.ENGLISH, state.locale != Locale.ENGLISH))
                           )
        }
    }
}