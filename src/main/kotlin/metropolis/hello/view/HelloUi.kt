package metropolis.hello.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.hello.data.City
import metropolis.hello.data.Country
import metropolis.xtractedExplorer.controller.lazyloading.LazyTableAction
import metropolis.xtractedExplorer.model.TableState
import metropolis.xtractedExplorer.view.Table

//@Composable
//fun ApplicationScope.HelloWindow(state: City) {
//    Window(onCloseRequest = ::exitApplication,
//           title          = state.name,
//           state          = rememberWindowState(width    = 400.dp,
//                                                height   = 200.dp,
//                                                position = WindowPosition(Alignment.Center))) {
//
//        HelloUi(state)
//    }
//}
//
//@Composable
//private fun HelloUi(state: City) {
//    Column(modifier            = Modifier.fillMaxSize(),
//           horizontalAlignment = Alignment.CenterHorizontally,
//           verticalArrangement = Arrangement.Center) {
//        Text(text     = "Hello ${state.name}",
//             fontSize = 42.sp)
//        VulcanSalute()
//    }
//}
@Composable
fun ApplicationScope.CountryExplorerWindow(state       : TableState<Country>,
                                           dataProvider: (Int) -> Country,
                                           idProvider  : (Country) -> Int,
                                           trigger     : (LazyTableAction) -> Unit) {
    Window(title          = state.title,
        onCloseRequest = ::exitApplication,
        state          = rememberWindowState(width    = 600.dp,
            height   = 500.dp,
            position = WindowPosition(Alignment.Center))) {

        CountryExplorerUI(state, dataProvider, idProvider, trigger)
    }
}

@Composable
fun CountryExplorerUI(state       : TableState<Country>,
                      dataProvider: (Int) -> Country,
                      idProvider  : (Country) -> Int,
                      trigger     : (LazyTableAction) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()
        .background(Color(0xFFEEEEEE))
        .padding(10.dp)) {
        Table(tableState   = state,
            itemProvider = dataProvider,
            idProvider   = idProvider,
            trigger      = trigger,
            modifier     = Modifier.weight(1.0f))
    }
}

@Composable
fun ApplicationScope.CityExplorerWindow(state       : TableState<City>,
                                           dataProvider: (Int) -> City,
                                           idProvider  : (City) -> Int,
                                           trigger     : (LazyTableAction) -> Unit) {
    Window(title          = state.title,
        onCloseRequest = ::exitApplication,
        state          = rememberWindowState(width    = 600.dp,
            height   = 500.dp,
            position = WindowPosition(Alignment.Center))) {

        CityExplorerUI(state, dataProvider, idProvider, trigger)
    }
}


@Composable
fun CityExplorerUI(state       : TableState<City>,
                      dataProvider: (Int) -> City,
                      idProvider  : (City) -> Int,
                      trigger     : (LazyTableAction) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()
        .background(Color(0xFFEEEEEE))
        .padding(10.dp)) {
        Table(tableState   = state,
            itemProvider = dataProvider,
            idProvider   = idProvider,
            trigger      = trigger,
            modifier     = Modifier.weight(1.0f))
    }
}