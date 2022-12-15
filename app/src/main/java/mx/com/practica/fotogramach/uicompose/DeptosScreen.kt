package mx.com.practica.fotogramach.uicompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import mx.com.practica.fotogramach.R
import mx.com.practica.fotogramach.composables.BackNavigationItem
import mx.com.practica.fotogramach.model.Depto
import mx.com.practica.fotogramach.utils.GRID_SPAN_COUNT

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun DeptosScreen(
    onNavigationIconClick: () -> Unit,
    deptosList: List<Depto>,
    onDeptoSelect: (Depto) -> Unit,
) {
    Scaffold(topBar = { BackNavigationItem(onNavigationIconClick) }) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Departamentos por tienda",
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
                items(deptosList) {
                    DeptosGridItem(depto = it, onDeptoSelect = onDeptoSelect)
                }
            })
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun DeptosGridItem(
    depto: Depto,
    onDeptoSelect: (Depto) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .height(100.dp)
            .width(100.dp),
        onClick = { onDeptoSelect(depto) },
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(R.drawable.ic_depto),
                contentDescription = null,
                modifier = Modifier
                    .background(Color.White)
                    .size(50.dp)
            )
            Text(
                text = depto.nombreDpto,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 8.sp,
            )
        }
    }
}