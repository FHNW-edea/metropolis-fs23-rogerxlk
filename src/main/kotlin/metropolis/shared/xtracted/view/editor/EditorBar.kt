package metropolis.shared.xtracted.view.editor

import java.util.*
import androidx.compose.runtime.Composable
import metropolis.shared.xtracted.controller.editor.EditorAction
import metropolis.shared.xtracted.model.editor.CH
import metropolis.shared.xtracted.model.editor.EditorState
import metropolis.shared.xtracted.model.undo.UndoState
import metropolis.shared.xtracted.view.ActionIconStrip

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