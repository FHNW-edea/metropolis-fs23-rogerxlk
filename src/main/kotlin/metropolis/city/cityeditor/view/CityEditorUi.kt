package metropolis.city.cityeditor.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.city.cityeditor.controller.Id
import metropolis.shared.data.City
import metropolis.shared.xtracted.controller.editor.EditorAction
import metropolis.shared.xtracted.model.editor.Attribute
import metropolis.shared.xtracted.model.editor.EditorState
import metropolis.shared.xtracted.model.editor.get
import metropolis.shared.xtracted.model.undo.UndoState
import metropolis.shared.xtracted.view.editor.EditorBar
import metropolis.shared.xtracted.view.editor.Form
import metropolis.shared.xtracted.view.editor.VSpace


@Composable
fun ApplicationScope.CityEditorWindow(state: EditorState<City>, undoState: UndoState, trigger : (EditorAction) -> Unit) {

    Window(title          = state.title.translate(state.locale),
           onCloseRequest = ::exitApplication,
           state          = rememberWindowState(width    = 700.dp,
                                                height   = 900.dp,
                                                position = WindowPosition(Alignment.Center))) {

        CityEditorUi(state, undoState, trigger)
    }
}

@Composable
fun CityEditorUi(state: EditorState<City>, undoState: UndoState, trigger : (EditorAction) -> Unit) {
    Column{
        EditorBar(state, undoState, trigger)

        Header(state)

        Card(modifier = Modifier.padding(10.dp)
                                .weight(1.0f)) {
            Form(state   = state,
                 trigger = trigger)
        }
    }
}



@Composable
private fun Header(state: EditorState<City>) {
    // im Editor-State werden die Attribute verwaltet. Diese können generisch als Formular angezeigt werden
    // der Header ist jedoch speziell, nicht generisch (oder noch nicht)
    val name    : Attribute<String> = state[Id.NAME]

    val huge       = 42.sp
    val large      = 18.sp

    Row(modifier = Modifier.height(IntrinsicSize.Max).padding(10.dp)){
        Column(modifier = Modifier.weight(1.0f)) {
            name.value?.let { Headline(text = it.format("??"), fontSize = huge) }
            VSpace(10.dp)
        }
    }
}

@Composable
private fun Headline(text: String, fontSize: TextUnit){
    Text(text       = text,
         maxLines   = 1,
         overflow   = TextOverflow.Ellipsis,
         fontSize   = fontSize,
         fontWeight = FontWeight.ExtraLight)
}