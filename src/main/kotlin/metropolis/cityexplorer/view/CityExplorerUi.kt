package metropolis.cityexplorer.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.shareddata.City
import metropolis.xtracted.xtractedExplorer.controller.lazyloading.LazyTableAction
import metropolis.xtracted.xtractedExplorer.model.TableState
import metropolis.xtracted.xtractedExplorer.view.ActionIconStrip
import metropolis.xtracted.xtractedExplorer.view.AlignLeftRight
import metropolis.xtracted.xtractedExplorer.view.Table
import metropolis.xtracted.xtractedExplorer.view.Toolbar

@Composable
fun ApplicationScope.CityExplorerWindow(
    state: TableState<City>,
    dataProvider: (Int) -> City,
    idProvider: (City) -> Int,
    trigger: (LazyTableAction) -> Unit
) {
    Window(
        title = state.title,
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            width = 600.dp,
            height = 500.dp,
            position = WindowPosition(Alignment.Center)
        )
    ) {

        CityExplorerUI(state, dataProvider, idProvider, trigger)
    }
}


@Composable
fun CityExplorerUI(
    state: TableState<City>,
    dataProvider: (Int) -> City,
    idProvider: (City) -> Int,
    trigger: (LazyTableAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(10.dp)
    ) {
        Toolbar {
            AlignLeftRight {
                ActionIconStrip(trigger = trigger,
                    listOf(LazyTableAction.AddItem(City(-999, "New")))
                )
            }
        }
        Table(
            tableState = state,
            itemProvider = dataProvider,
            idProvider = idProvider,
            trigger = trigger,
            modifier = Modifier.weight(1.0f)
        )
    }
}