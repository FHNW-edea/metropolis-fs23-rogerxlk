package metropolis.xtracted.xtractedEditor.controller.editor

import java.util.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import metropolis.xtracted.xtractedEditor.model.Attribute
import metropolis.xtracted.xtractedExplorer.controller.Action

sealed class EditorAction(
        override val name   : String,
        override val icon   : ImageVector? = null,
        override val enabled: Boolean      = true) : Action {

    // Most important Action of an editor: Update the value of an attribute
    class  Update(val attribute: Attribute<*>, val value: String) : metropolis.xtracted.xtractedEditor.controller.editor.EditorAction("Update ${attribute.id.translate(Locale.ENGLISH)}", null, !attribute.readOnly)

    class  Save(enabled: Boolean)        : metropolis.xtracted.xtractedEditor.controller.editor.EditorAction("Save",   Icons.Filled.Save,   enabled)
    object Reload                        : metropolis.xtracted.xtractedEditor.controller.editor.EditorAction("Reload", Icons.Filled.Cached)

    class  Undo(enabled: Boolean = true) : metropolis.xtracted.xtractedEditor.controller.editor.EditorAction("Undo", Icons.Filled.Undo, enabled)
    class  Redo(enabled: Boolean = true) : metropolis.xtracted.xtractedEditor.controller.editor.EditorAction("Redo", Icons.Filled.Redo, enabled)
    object  Delete                       : metropolis.xtracted.xtractedEditor.controller.editor.EditorAction("Delete", Icons.Filled.Delete)

    class  SetLocale(val locale: Locale, enabled: Boolean) : metropolis.xtracted.xtractedEditor.controller.editor.EditorAction(locale.isO3Language, null, enabled)

}