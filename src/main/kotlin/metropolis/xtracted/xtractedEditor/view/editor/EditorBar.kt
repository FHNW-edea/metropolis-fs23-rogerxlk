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
fun EditorBar(state: EditorState<*>, undoState: UndoState, trigger: (EditorAction) -> Unit) {
    Toolbar {
        AlignLeftRight {
            ActionIconStrip(
                trigger,
                listOf(
                    EditorAction.Save(state.changed && state.valid),
                    EditorAction.Reload
                ),

                listOf(
                    EditorAction.Undo(undoState.undoAvailable),
                    EditorAction.Redo(undoState.redoAvailable)
                ),
                listOf(EditorAction.Delete)
            )

            ActionIconStrip(
                trigger,
                listOf(
                    EditorAction.SetLocale(CH, state.locale != CH),
                    EditorAction.SetLocale(Locale.ENGLISH, state.locale != Locale.ENGLISH)
                )
            )
        }
    }
}