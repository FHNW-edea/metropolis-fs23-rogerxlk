package metropolis.xtracted.xtractedExplorer.controller

import androidx.compose.ui.graphics.vector.ImageVector

interface Action {
    val name   : String
    val icon   : ImageVector?
    val enabled: Boolean
}