package metropolis.metropolistool.controller

import androidx.compose.ui.graphics.vector.ImageVector
import metropolis.xtracted.xtractedExplorer.controller.Action

sealed class MetropolisAction(
    override val name: String,
    override val icon: ImageVector? = null,
    override val enabled: Boolean
) : Action {
    object SelectCity : MetropolisAction("Select City", null, true)
    object SelectCountry : MetropolisAction("Select Country", null, true)
}