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
    class AddItem<T>(val item: T)                                         : LazyTableAction("Add Item", icon = Icons.Filled.AddCircle)
    class RemoveItem<T>(val item: T)                                      : LazyTableAction("Remove Item", icon = Icons.Filled.RemoveCircle, enabled = false)
    class UpdateItem<T>(val item: T)                                      : LazyTableAction("Update Item", icon = Icons.Filled.Edit, enabled = false)
    object SelectNext                                                     : LazyTableAction("Select Next Item")
    object SelectPrevious                                                 : LazyTableAction("Select Next Item")
}