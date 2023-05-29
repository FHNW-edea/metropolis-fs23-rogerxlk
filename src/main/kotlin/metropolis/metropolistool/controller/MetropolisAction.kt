package metropolis.metropolistool.controller

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.ui.graphics.vector.ImageVector
import metropolis.shareddata.City
import metropolis.shareddata.Country
import metropolis.xtractedExplorer.controller.Action
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableAction

sealed class MetropolisAction(
    override val name: String,
    override val icon: ImageVector? = null,
    override val enabled: Boolean = true
) : Action {
//    class AddCity : MetropolisAction("New City", Icons.Filled.AddCircle, true)
//    class AddCountry : MetropolisAction("New Country", Icons.Filled.AddCircle, true)
//    class UpdateCity(val id: Int, enabled: Boolean) : MetropolisAction("Update City", null, enabled = enabled)
//    class UpdateCountry(val id: Int, enabled: Boolean) : MetropolisAction("Update Country", null, enabled = enabled)
//    class RemoveCity(val id: Int, enabled: Boolean) : MetropolisAction("Delete City", Icons.Filled.RemoveCircle, enabled = enabled)
//    class RemoveCountry(val id: Int, enabled: Boolean) : MetropolisAction("Delete Country", Icons.Filled.RemoveCircle, enabled = enabled)
    class AddItem<T>(val item: T)                                         : MetropolisAction("Add Item", icon = Icons.Filled.AddCircle)
    class RemoveItem<T>(val item: T, enabled: Boolean)                    : MetropolisAction("Remove Item", icon = Icons.Filled.RemoveCircle, enabled = enabled)
    class UpdateItem<T>(val item: T, enabled: Boolean)                    : MetropolisAction("Update Item", icon = Icons.Filled.Edit, enabled = enabled)
}