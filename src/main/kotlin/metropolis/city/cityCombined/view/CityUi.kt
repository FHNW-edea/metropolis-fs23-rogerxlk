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
import metropolis.shared.data.City
import metropolis.shared.xtracted.controller.editor.EditorController
import metropolis.shared.xtracted.controller.explorer.LazyTableController

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
        metropolis.shared.data.ControllerType.CITY_EXPLORER -> showExplorerUi(state.activeController as LazyTableController<City>)
        metropolis.shared.data.ControllerType.CITY_EDITOR -> showEditorUi(state.activeController as EditorController<City>)
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
