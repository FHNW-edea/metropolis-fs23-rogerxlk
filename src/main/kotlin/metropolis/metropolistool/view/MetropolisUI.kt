package metropolis.metropolistool.view

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cityCombined.controller.CityController
import metropolis.cityCombined.view.CityUi
import metropolis.cityexplorer.view.CityExplorerUI
import metropolis.countryCombined.controller.CountryController
import metropolis.countryCombined.view.CountryUi
import metropolis.countryexplorer.view.CountryExplorerUI
import metropolis.metropolistool.controller.MetropolisAction
import metropolis.metropolistool.controller.MetropolisController
import metropolis.shareddata.City
import metropolis.shareddata.ControllerType
import metropolis.shareddata.Country
import metropolis.xtracted.xtractedExplorer.controller.lazyloading.LazyTableController

@Composable
fun ApplicationScope.MetropolisWindow(controller: MetropolisController) {
    Window(
        title = controller.state.title,
        onCloseRequest = { exitApplication() },
        state = rememberWindowState(
            width = 1000.dp,
            height = 1000.dp,
            position = WindowPosition(Alignment.Center)
        )
    ) {
        MetropolisUi(controller, trigger = { controller.executeAction(it) })
    }
}

@Composable
fun MetropolisUi(controller: MetropolisController,
                 trigger: (MetropolisAction) -> Unit,
) {
    val (selectedTab, onTabSelected) = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(selected = selectedTab == 0, onClick = { onTabSelected(0); trigger(MetropolisAction.SelectCity) }) {
                    Text("Cities")
                }
                Tab(selected = selectedTab == 1, onClick = { onTabSelected(1); trigger(MetropolisAction.SelectCountry) }) {
                    Text("Countries")
                }
            }
        },
        content = {
            when (controller.state.controllerType) {
                ControllerType.CITY -> CityUi(controller.state.activeController as CityController)
                ControllerType.COUNTRY -> CountryUi(controller.state.activeController as CountryController)
                else -> {}
            }
        }
    )
}
