package metropolis.countryeditor.view

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
import metropolis.countryeditor.controller.Id
import metropolis.data.Country
import metropolis.xtractedEditor.controller.editor.EditorAction
import metropolis.xtractedEditor.model.*
import metropolis.xtractedEditor.view.VSpace
import metropolis.xtractedEditor.view.editor.EditorBar
import metropolis.xtractedEditor.view.editor.Form

@Composable
fun ApplicationScope.CountryEditorWindow(state: EditorState<Country>, undoState: UndoState, trigger : (EditorAction) -> Unit) {

    Window(title          = state.title.translate(state.locale),
           onCloseRequest = ::exitApplication,
           state          = rememberWindowState(width    = 700.dp,
                                                height   = 900.dp,
                                                position = WindowPosition(Alignment.Center))) {

        CountryEditorUi(state, undoState, trigger)
    }
}

@Composable
fun CountryEditorUi(state: EditorState<Country>, undoState: UndoState, trigger : (EditorAction) -> Unit) {
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
private fun Header(state: EditorState<Country>) {
    // im Editor-State werden die Attribute verwaltet. Diese können generisch als Formular angezeigt werden
    // der Header ist jedoch speziell, nicht generisch (oder noch nicht)
    val name    : Attribute<String>       = state[Id.NAME]


    val huge       = 42.sp
    val large      = 18.sp

    Row(modifier = Modifier.height(IntrinsicSize.Max).padding(10.dp)){
        Column(modifier = Modifier.weight(1.0f)) {
            Headline(text = name.value.format("??"), fontSize = huge)
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