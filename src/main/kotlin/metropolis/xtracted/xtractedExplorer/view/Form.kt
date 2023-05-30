package metropolis.xtracted.xtractedExplorer.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import metropolis.xtracted.xtractedExplorer.model.Attribute

@Composable
fun<T: Any> AttributeField(attribute: Attribute<T>, onValueChange : (String) -> Unit, password: Boolean = false, modifier: Modifier){
    OutlinedTextField(modifier             = modifier,
                      value                = attribute.valueAsText,
                      isError              = !attribute.isValid,
                      visualTransformation = if(password) PasswordVisualTransformation() else VisualTransformation.None,
                      onValueChange        = onValueChange)
}


@Composable
fun<T: Any> FormField(attribute: Attribute<T>, onValueChange: (String) -> Unit, captionWidth: Dp = 100.dp, password: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier          = Modifier.fillMaxWidth()){
        Text(text     = attribute.caption,
             modifier = Modifier.width(captionWidth))
        AttributeField(attribute     = attribute,
                       onValueChange = onValueChange,
                       password      = password,
                       modifier      = Modifier.weight(1.0f)
                                               .background(Color.White))
    }
}

@Composable
fun BooleanFormField(attribute: Attribute<Boolean>, onValueChange: (Boolean) -> Unit, captionWidth: Dp = 100.dp, enabled: Boolean = true) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier          = Modifier.fillMaxWidth()) {
        Text(text     = attribute.caption,
             modifier = Modifier.width(captionWidth))

        Switch(checked         = attribute.value,
               enabled         = enabled,
               onCheckedChange = { onValueChange(it) }
              )
    }
}


@Composable
fun<T: Any> SelectionFormField(attribute: Attribute<T>, allItems: List<T>, listItem: @Composable (T) -> Unit, onItemClick: (T) -> Unit, captionWidth: Dp = 100.dp){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier          = Modifier.fillMaxWidth()) {
        Text(text     = attribute.caption,
             modifier = Modifier.width(captionWidth))

        SelectionBox(currentSelectionTitle = attribute.valueAsText,
                     allItems              = allItems.filter { it != attribute.value },
                     onClick               = {},
                     onItemClick           = { onItemClick(it) },
                     listItem              = { listItem(it) }
                    )
    }
}

@Composable
fun<T : Any> ReadOnlyFormField(attribute: Attribute<T>, captionWidth: Dp = 100.dp) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier          = Modifier.fillMaxWidth()) {
        Text(text     = attribute.caption,
             modifier = Modifier.width(captionWidth))

        Text(attribute.valueAsText)
    }
}
