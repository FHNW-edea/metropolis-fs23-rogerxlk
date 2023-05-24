package metropolis.combined

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
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
import metropolis.data.City
import metropolis.data.Country
import metropolis.xtractedEditor.view.VSpace
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableAction
import metropolis.xtractedExplorer.model.TableState
import metropolis.xtractedExplorer.view.Table

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
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }
    val titles = listOf(cityState.title, countryState.title)

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(10.dp)
    ) {
        TabRow(selectedTabIndex = selectedTab, backgroundColor = Color(0xFFD1C4E9)) {
            titles.forEachIndexed { index, title ->
                Tab(selected = selectedTab == index,
                    onClick = { setSelectedTab(index) },
                    text = { Text(title, modifier = Modifier.padding(horizontal = 10.dp)) })
            }
        }

        when (selectedTab) {
            0 -> {
                VSpace(10.dp)
                Button(onClick = { /* Add new entry to City table here */ }) {
                    Text("Add new City")
                }
                VSpace(10.dp)
                CityExplorerUI(cityState, cityDataProvider, cityIdProvider, cityTrigger)
            }
            1 -> {
                VSpace(10.dp)
                Button(onClick = { /* Add new entry to Country table here */ }) {
                    Text("Add new Country")
                }
                VSpace(10.dp)
                CountryExplorerUI(countryState, countryDataProvider, countryIdProvider, countryTrigger)
            }
        }
        VSpace(10.dp)
    }
}

