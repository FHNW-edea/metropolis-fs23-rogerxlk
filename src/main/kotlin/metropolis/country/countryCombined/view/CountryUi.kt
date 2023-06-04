package metropolis.country.countryCombined.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.country.countryCombined.controller.CountryController
import metropolis.country.countryeditor.view.CountryEditorUi
import metropolis.country.countryexplorer.view.CountryExplorerUI
import metropolis.shared.data.Country
import metropolis.shared.xtracted.controller.editor.EditorController
import metropolis.shared.xtracted.controller.explorer.LazyTableController

@Composable
fun ApplicationScope.CountryWindow(controller: CountryController) {
    Window(title          = controller.state.title,
        onCloseRequest = { exitApplication() },
        state          = rememberWindowState(width    = 580.dp,
            height   = 500.dp,
            position = WindowPosition(Alignment.Center)
        )
    ) {
        CountryUi(controller)
    }
}

@Composable
fun CountryUi(controller: CountryController) {
    val state = controller.state
    when (state.controllerType) {
        metropolis.shared.data.ControllerType.COUNTRY_EXPLORER -> showExplorerUi(state.activeController as LazyTableController<Country>)
        metropolis.shared.data.ControllerType.COUNTRY_EDITOR -> showEditorUi(state.activeController as EditorController<Country>)
        else -> {}
    }
}

@Composable
fun showEditorUi(editorController: EditorController<Country>) {
    CountryEditorUi(state = editorController.state,
        undoState = editorController.undoState,
        trigger = { editorController.triggerAction(it) })
}

@Composable
fun showExplorerUi(lazyTableController: LazyTableController<Country>) {
    CountryExplorerUI(state = lazyTableController.state,
        dataProvider = { lazyTableController.getData(it) },
        idProvider = { it.id },
        trigger = { lazyTableController.triggerAction(it) }
    )
}