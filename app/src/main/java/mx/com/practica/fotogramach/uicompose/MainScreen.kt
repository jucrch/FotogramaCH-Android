package mx.com.practica.fotogramach.uicompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.com.practica.fotogramach.AuthNavDestinations.DeptosScreenDestination
import mx.com.practica.fotogramach.AuthNavDestinations.MainScreenDestination
import mx.com.practica.fotogramach.AuthNavDestinations.PasillosScreenDestination
import mx.com.practica.fotogramach.model.Depto
import mx.com.practica.fotogramach.model.TiendasAsignadas

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun MainScreen(
) {
    val navController = rememberNavController()
    AuthNavHost(
        navController = navController,
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
private fun AuthNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenDestination,
    ) {
        composable(route = MainScreenDestination) {
            TiendasAsigScreen(
                onTiendaSelect = {
                    navController.navigate(
                        route = DeptosScreenDestination,
                    )
                },
                tiendasAsignadasList = listOf(
                    TiendasAsignadas(
                        1001, "Chederaui"
                    ),
                    TiendasAsignadas(
                        1002, "AGO"
                    ),
                ),
//                authViewModel = authViewModel,
//                onTiendaSelect = onTiendaSelect,
            )
        }

        composable(route = DeptosScreenDestination) {
            DeptosScreen(
                onNavigationIconClick = { navController.navigate(route = MainScreenDestination) },
                deptosList = listOf(
                    Depto(1, 1, "Abarrotes"),
                    Depto(1, 2, "Perfumería"),
                    Depto(1, 3, "Cava"),
                    Depto(1, 4, "Papelería")
                ),
                onDeptoSelect = {
                    navController.navigate(
                        route = PasillosScreenDestination,
                    )
                }
//                onSignUpButtonClick = onSignUpButtonClick,
//                authViewModel = authViewModel
            )
        }

        composable(route = PasillosScreenDestination) {
            PasillosScreen(
                onNavigationIconClick = { navController.navigate(route = DeptosScreenDestination) },
//                onSignUpButtonClick = onSignUpButtonClick,
//                authViewModel = authViewModel
            )
        }
    }
}