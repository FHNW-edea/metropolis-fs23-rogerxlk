package metropolis.metropolis.view

import androidx.compose.foundation.background
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
import metropolis.city.cityCombined.controller.CityController
import metropolis.city.cityCombined.view.CityUi
import metropolis.country.countryCombined.controller.CountryController
import metropolis.country.countryCombined.view.CountryUi
import metropolis.metropolis.controller.MetropolisController
import metropolis.shared.data.ControllerType

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
            TabRow(backgroundColor = Color(0xFFD1C4E9),
                contentColor = MaterialTheme.colors.primary,
                selectedTabIndex = selectedTab) {
                Tab(modifier = Modifier.padding(vertical = 10.dp),
                    selected = selectedTab == 0,
                    onClick = { onTabSelected(0); controller.switchToCityTable() }) {
                    Text(controller.cityController.state.title)
                }
                Tab(modifier = Modifier.padding(vertical = 10.dp),
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