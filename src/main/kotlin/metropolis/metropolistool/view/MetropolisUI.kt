package metropolis.metropolistool.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cityexplorer.view.CityExplorerUI
import metropolis.countryexplorer.view.CountryExplorerUI
import metropolis.shareddata.City
import metropolis.shareddata.Country
import metropolis.xtractedEditor.view.VSpace
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableAction
import metropolis.xtractedExplorer.model.TableState

@Composable
fun ApplicationScope.MetropolisWindow(
    cityState: TableState<City>,
    cityDataProvider: (Int) -> City,
    cityIdProvider: (City) -> Int,
    countryState: TableState<Country>,
    countryDataProvider: (Int) -> Country,
    countryIdProvider: (Country) -> Int,
    cityTrigger: (LazyTableAction) -> Unit,
    countryTrigger: (LazyTableAction) -> Unit
) {
    Window(
        title = "Metropolis",
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            width = 1000.dp,
            height = 1000.dp,
            position = WindowPosition(Alignment.Center)
        )
    ) {

        MetropolisUi(
            cityState,
            cityDataProvider,
            cityIdProvider,
            countryState,
            countryDataProvider,
            countryIdProvider,
            cityTrigger,
            countryTrigger
        )
    }
}

@Composable
fun MetropolisUi(
    cityState: TableState<City>,
    cityDataProvider: (Int) -> City,
    cityIdProvider: (City) -> Int,
    countryState: TableState<Country>,
    countryDataProvider: (Int) -> Country,
    countryIdProvider: (Country) -> Int,
    cityTrigger: (LazyTableAction) -> Unit,
    countryTrigger: (LazyTableAction) -> Unit
) {
    val (selectedTab, onTabSelected) = remember { mutableStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(10.dp),
        topBar = {
            TabRow(selectedTabIndex = selectedTab, backgroundColor = Color(0xFFD1C4E9)) {
                Tab(selected = selectedTab == 0, modifier = Modifier.padding(10.dp), onClick = { onTabSelected(0) }) {
                    Text(cityState.title)
                }
                Tab(selected = selectedTab == 1, modifier = Modifier.padding(10.dp), onClick = { onTabSelected(1) }) {
                    Text(countryState.title)
                }
            }
        },
        content = {

            when (selectedTab) {
                0 -> {
                    VSpace(10.dp)
                    CityExplorerUI(cityState, cityDataProvider, cityIdProvider, cityTrigger)
                }

                1 -> {
                    VSpace(10.dp)
                    CountryExplorerUI(countryState, countryDataProvider, countryIdProvider, countryTrigger)
                }
            }
            VSpace(10.dp)
        }
    )
}

