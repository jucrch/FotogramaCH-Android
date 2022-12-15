package mx.com.practica.fotogramach.uicompose

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import mx.com.practica.fotogramach.R
import mx.com.practica.fotogramach.composables.BackNavigationIcon
import mx.com.practica.fotogramach.model.TiendasAsignadas
import mx.com.practica.fotogramach.utils.GRID_SPAN_COUNT

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun TiendasAsigScreen(
    tiendasAsignadasList: List<TiendasAsignadas>,
    onTiendaSelect: (TiendasAsignadas) -> Unit,
) {
    Scaffold(topBar = { BackNavigationIcon() }) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tiendas asignadas",
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
            LazyVerticalGrid(cells = GridCells.Fixed(GRID_SPAN_COUNT), content = {
                items(tiendasAsignadasList) {
                    TiendasAsignadasGridItem(tiendasAsignadas = it, onTiendaSelect = onTiendaSelect)
                }
            })
        }
    }
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun TiendasAsignadasGridItem(
    tiendasAsignadas: TiendasAsignadas,
    onTiendaSelect: (TiendasAsignadas) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .height(100.dp)
            .width(100.dp),
        onClick = { onTiendaSelect(tiendasAsignadas) },
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(R.drawable.ic_tiendaasignada),
                contentDescription = null,
                modifier = Modifier
                    .background(Color.White)
                    .size(50.dp)
            )
            Text(
                text = tiendasAsignadas.nombreTienda,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
            )
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview
@Composable
fun TiendasAsgScreenPreview() {

    TiendasAsigScreen(
        tiendasAsignadasList = listOf(
            TiendasAsignadas(
                1001, "Chederaui"
            ),
            TiendasAsignadas(
                1002, "AGO"
            ),
        ),
        onTiendaSelect = { tiendasAsignadas -> Log.e("miTag", "${tiendasAsignadas.idTienda}") }
    )
}