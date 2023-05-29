package metropolis.xtractedExplorer.controller.lazyloading

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.ui.graphics.vector.ImageVector

import metropolis.xtractedExplorer.controller.Action
import metropolis.xtractedExplorer.model.TableColumn

sealed class LazyTableAction(
        override val name   : String,
        override val icon   : ImageVector? = null,
        override val enabled: Boolean = true) : Action {

    class Select(val id: Int)                                             : LazyTableAction("Select Item")
    class ToggleSortOrder<T>(val column: TableColumn<T, *>)               : LazyTableAction("Change Sort Order")
    class SetFilter<T>(val column: TableColumn<T, *>, val filter: String) : LazyTableAction("Set Filter")
    object SelectNext                                                     : LazyTableAction("Select Next Item")
    object SelectPrevious                                                 : LazyTableAction("Select Next Item")
}