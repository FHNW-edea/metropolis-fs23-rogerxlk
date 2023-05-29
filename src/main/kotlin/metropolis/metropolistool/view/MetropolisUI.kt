package metropolis.metropolistool.view

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
import metropolis.cityeditor.view.CityEditorUi
import metropolis.cityexplorer.view.CityExplorerUI
import metropolis.countryeditor.view.CountryEditorUi
import metropolis.countryexplorer.view.CountryExplorerUI
import metropolis.metropolistool.controller.Editor
import metropolis.metropolistool.controller.MetropolisAction
import metropolis.metropolistool.controller.MetropolisController
import metropolis.metropolistool.controller.MetropolisState
import metropolis.shareddata.City
import metropolis.shareddata.Country
import metropolis.xtractedEditor.view.VSpace

@Composable
fun ApplicationScope.MetropolisWindow(
    controller: MetropolisController,
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
            state = controller.state,
            cityDataProvider = { id -> controller.state.cityEditorController?.repository?.read(id)!! },
            cityIdProvider = { city -> city.id },
            countryDataProvider = { id -> controller.state.countryEditorController?.repository?.read(id)!! },
            countryIdProvider = { country -> country.id },
            metropolisTrigger = controller::triggerAction
        )
    }
}

@Composable
fun MetropolisUi(
    state: MetropolisState,
    cityDataProvider: (Int) -> City,
    cityIdProvider: (City) -> Int,
    countryDataProvider: (Int) -> Country,
    countryIdProvider: (Country) -> Int,
    metropolisTrigger: (MetropolisAction) -> Unit
) {
    val (selectedTab, onTabSelected) = remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFEEEEEE))
            .padding(10.dp),
        topBar = {
            TabRow(selectedTabIndex = selectedTab, backgroundColor = Color(0xFFD1C4E9)) {
                Tab(selected = selectedTab == 0, modifier = Modifier.padding(10.dp), onClick = { onTabSelected(0) }) {
                    Text(state.cityExplorerController.state.title)
                }
                Tab(selected = selectedTab == 1, modifier = Modifier.padding(10.dp), onClick = { onTabSelected(1) }) {
                    Text(state.countryExplorerController.state.title)
                }
            }
        },
        content = {
            when (state.activeEditor) {
                Editor.CITY_EDITOR -> {
                    CityEditorUi(state = state.cityEditorController?.state!!,
                        undoState = state.cityEditorController.undoState,
                        trigger = { state.cityEditorController.triggerAction(it) })
                }

                Editor.COUNTRY_EDITOR -> {
                    CountryEditorUi(state = state.countryEditorController?.state!!,
                        undoState = state.countryEditorController.undoState,
                        trigger = { state.countryEditorController.triggerAction(it) })
                }

                else -> {
                    when (selectedTab) {
                        0 -> {
                            CityExplorerUI(
                                state.cityExplorerController.state,
                                cityDataProvider,
                                cityIdProvider,
                                trigger = { state.cityExplorerController.triggerAction(it) },
                                metropolisTrigger
                            )
                        }

                        1 -> {
                            CountryExplorerUI(
                                state.countryExplorerController.state,
                                countryDataProvider,
                                countryIdProvider,
                                trigger = { state.countryExplorerController.triggerAction(it) },
                                metropolisTrigger
                            )
                        }
                    }
                }
            }
            VSpace(10.dp)
        }
    )
}

