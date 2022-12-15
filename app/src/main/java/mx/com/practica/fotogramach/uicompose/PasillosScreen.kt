package mx.com.practica.fotogramach.uicompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.com.practica.fotogramach.R
import mx.com.practica.fotogramach.composables.BackNavigationItem

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PasillosScreen(
    onNavigationIconClick: () -> Unit,
) {
    Scaffold(topBar = { BackNavigationItem(onNavigationIconClick) }) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pasillos por Departamento",
                color = colorResource(id = R.color.colorAccent),
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            )
            Divider(
                modifier = Modifier.padding(
                    top = 1.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                ), color = colorResource(id = R.color.divider), thickness = 1.dp
            )
        }
    }
}