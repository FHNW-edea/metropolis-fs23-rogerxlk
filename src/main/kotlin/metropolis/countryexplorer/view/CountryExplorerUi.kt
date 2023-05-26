package metropolis.countryexplorer.view

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
import metropolis.shareddata.Country
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableAction
import metropolis.xtractedExplorer.model.TableState
import metropolis.xtractedExplorer.view.*

@Composable
fun ApplicationScope.CountryExplorerWindow(
    state: TableState<Country>,
    dataProvider: (Int) -> Country,
    idProvider: (Country) -> Int,
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

        CountryExplorerUI(state, dataProvider, idProvider, trigger)
    }
}

@Composable
fun CountryExplorerUI(
    state: TableState<Country>,
    dataProvider: (Int) -> Country,
    idProvider: (Country) -> Int,
    trigger: (LazyTableAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(10.dp)
    ) {
        Toolbar {
            AlignLeftRight {
                ActionIconStrip(
                    trigger = trigger,
                    listOf(
                        LazyTableAction.AddItem(
                            item = idProvider(
                                Country(
                                    id = -999,
                                    name = "",
                                    population = 0,
                                    areaInSqKm = 0.0,
                                    isoAlpha2 = "",
                                    isoAlpha3 = "",
                                    continent = "",
                                    geonameId = 0
                                )
                            )
                        )
                    ),
                    listOf(LazyTableAction.RemoveItem(item = state.selectedId?.let { dataProvider(it) }
                        ?.let { idProvider(it) },state.selectedId != null)), //todo: - correct?
                    listOf(LazyTableAction.UpdateItem(item = state.selectedId?.let { dataProvider(it) }
                        ?.let { idProvider(it) }, state.selectedId != null)), //todo: - correct?
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


