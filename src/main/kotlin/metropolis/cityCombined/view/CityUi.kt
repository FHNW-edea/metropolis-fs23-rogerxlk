package metropolis.cityCombined.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cityCombined.controller.CityController
import metropolis.cityeditor.view.CityEditorUi
import metropolis.cityexplorer.view.CityExplorerUI
import metropolis.shareddata.City
import metropolis.shareddata.ControllerType
import metropolis.xtractedEditor.controller.editor.EditorController
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableController

@Composable
fun ApplicationScope.CityWindow(controller: CityController) {
    Window(title          = controller.state.title,
        onCloseRequest = { exitApplication() },
        state          = rememberWindowState(width    = 580.dp,
            height   = 500.dp,
            position = WindowPosition(Alignment.Center)
        )
    ) {
        CityUi(controller)
    }
}

@Composable
fun CityUi(controller: CityController) {
    with(controller.state) {
        when (controllerType) {
            ControllerType.CITY_EXPLORER -> showExplorerUi(activeController as LazyTableController<City>)
            ControllerType.CITY_EDITOR -> showEditorUi(activeController as EditorController<City>)
            else -> {}
        }
    }
}

fun showEditorUi(editorController: EditorController<City>) {
    CityEditorUi(state = editorController.state,
        undoState = editorController.undoState,
        trigger = { editorController.triggerAction(it) })
}

fun showExplorerUi(lazyTableController: LazyTableController<City>) {
    CityExplorerUI(state = lazyTableController.state,
        dataProvider = { lazyTableController.getData(it) },
        idProvider = { it.id },
        trigger = { lazyTableController.triggerAction(it) }
    )
}
