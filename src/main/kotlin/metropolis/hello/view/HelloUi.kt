package metropolis.hello.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.hello.data.City
import metropolis.xtracted.view.VulcanSalute

@Composable
fun ApplicationScope.HelloWindow(state: City) {
    Window(onCloseRequest = ::exitApplication,
           title          = state.title,
           state          = rememberWindowState(width    = 400.dp,
                                                height   = 200.dp,
                                                position = WindowPosition(Alignment.Center))) {

        HelloUi(state)
    }
}

@Composable
private fun HelloUi(state: City) {
    Column(modifier            = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        Text(text     = "Hello ${state.title}",
             fontSize = 42.sp)
        VulcanSalute()
    }
}