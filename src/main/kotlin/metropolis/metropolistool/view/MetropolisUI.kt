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
import metropolis.countryCombined.controller.CountryController
import metropolis.countryCombined.view.CountryUi
import metropolis.metropolistool.controller.MetropolisController
import metropolis.shareddata.ControllerType

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
        MetropolisUi(controller)
    }
}

@Composable
fun MetropolisUi(
    controller: MetropolisController,
) {
    val (selectedTab, onTabSelected) = remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(selected = selectedTab == 0, onClick = { onTabSelected(0); controller.switchToCityTable() }) {
                    Text(controller.cityController.state.title)
                }
                Tab(
                    selected = selectedTab == 1,
                    onClick = { onTabSelected(1); controller.switchToCountryTable() }) {
                    Text(controller.countryController.state.title)
                }
            }
        },
        content = {
            val state = controller.state
            when (state.controllerType) {
                ControllerType.CITY -> CityUi(controller.state.activeController as CityController)
                ControllerType.COUNTRY -> CountryUi(controller.state.activeController as CountryController)
                else -> {}
            }
        }
    )
}