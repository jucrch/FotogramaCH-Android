package mx.com.practica.fotogramach.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import mx.com.practica.fotogramach.R

@Composable
fun AuthField(
    label: String,
    email: String,
    onTextChanged: (String) -> Unit,
    errorMessageId: Int?,
//    errorSemanticAction: String = "",
//    fieldSemantic: String = "",
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(modifier = modifier) {
        if (errorMessageId != null) {
            Text(
                text = stringResource(id = errorMessageId),
                modifier = Modifier
                    .fillMaxWidth()
//                    .semantics { testTag = errorSemanticAction },
            )
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
//                .semantics { testTag = fieldSemantic },
            label = { Text(text = label) },
            value = email,
            onValueChange = onTextChanged,
            visualTransformation = visualTransformation,
            isError = errorMessageId != null,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.colorAccent),
                unfocusedBorderColor = colorResource(id = R.color.color_primary)
            )
        )
    }
}