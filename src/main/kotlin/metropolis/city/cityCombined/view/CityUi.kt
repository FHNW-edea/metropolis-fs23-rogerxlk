package metropolis.city.cityCombined.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.city.cityCombined.controller.CityController
import metropolis.city.cityeditor.view.CityEditorUi
import metropolis.city.cityexplorer.view.CityExplorerUI
import metropolis.shareddata.City
import metropolis.shareddata.ControllerType
import metropolis.xtracted.xtractedEditor.controller.editor.EditorController
import metropolis.xtracted.xtractedExplorer.controller.lazyloading.LazyTableController

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
    val state = controller.state
    when (state.controllerType) {
        ControllerType.CITY_EXPLORER -> showExplorerUi(state.activeController as LazyTableController<City>)
        ControllerType.CITY_EDITOR -> showEditorUi(state.activeController as EditorController<City>)
        else -> {}
    }
}

@Composable
fun showEditorUi(editorController: EditorController<City>) {
    CityEditorUi(state = editorController.state,
        undoState = editorController.undoState,
        trigger = { editorController.triggerAction(it) })
}

@Composable
fun showExplorerUi(lazyTableController: LazyTableController<City>) {
    CityExplorerUI(state = lazyTableController.state,
        dataProvider = { lazyTableController.getData(it) },
        idProvider = { it.id },
        trigger = { lazyTableController.triggerAction(it) }
    )
}
