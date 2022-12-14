package mx.com.practica.fotogramach.composables

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import mx.com.practica.fotogramach.R


@Composable
fun ErrorDialog(messageId: Int, onDialogDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text(stringResource(R.string.error_dialog_title)) },
        text = { Text(stringResource(id = messageId)) },
        confirmButton = {
            Button(onClick = { onDialogDismiss() }) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    )
}