package mx.com.practica.fotogramach.composables

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import mx.com.practica.fotogramach.R

@Composable
fun BackNavigationIcon() {
    TopAppBar(
        title = {
            Text(stringResource(R.string.app_name))
        },
        backgroundColor = colorResource(id = R.color.color_primary),
        contentColor = Color.White,
    )
}