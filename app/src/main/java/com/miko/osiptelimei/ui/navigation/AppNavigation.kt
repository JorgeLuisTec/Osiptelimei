package com.miko.osiptelimei.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miko.osiptelimei.ui.view.ConsultaSreen
import com.miko.osiptelimei.ui.view.EmpresasRentesegScreen
import com.miko.osiptelimei.ui.view.FormularioCuartoScreen
import com.miko.osiptelimei.ui.view.FormularioPrimeroScreen


import com.miko.osiptelimei.ui.view.IncioScreen
import com.miko.osiptelimei.ui.view.RespuestaAlertConsultaScreen
import com.miko.osiptelimei.ui.view.RespuestaNormalConsultaScreen
import com.miko.osiptelimei.ui.view.FormularioPrincipalScreen
import com.miko.osiptelimei.ui.view.FormularioPruebaScreen
import com.miko.osiptelimei.ui.view.FormularioSegundoScreen
import com.miko.osiptelimei.ui.view.FormularioTerceroScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,

){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.IncioScreen.route ){
    //NavHost(navController = navController, startDestination = AppScreens.Drawer.route){
        composable(route = AppScreens.IncioScreen.route){
            IncioScreen(navHostController = navController, isDarkTheme = isDarkTheme) {
            }
        }
        composable(AppScreens.ConsultaScreen.route){
            ConsultaSreen(navHostController = navController, isDarkTheme = isDarkTheme, icon = icon,
            )
        }
        composable(AppScreens.RespuestaNormalConsultaScreen.route){
            RespuestaNormalConsultaScreen(navController,isDarkTheme = isDarkTheme, icon = { icon })
        }

        composable(AppScreens.RespuestaAlertConsutaScreen.route){
            RespuestaAlertConsultaScreen(navController,isDarkTheme = isDarkTheme, icon = { icon })
        }
        /*
        composable(AppScreens.HomeScreen.route){
            HomeScreen(navHostController = navController, isDarkTheme = isDarkTheme,icon = icon)
        }

         */
        composable(AppScreens.FormularioPrincipalScreen.route){
            FormularioPrincipalScreen(navHostController = navController, isDarkTheme =isDarkTheme, icon = icon ) {
            }
        }
        composable(AppScreens.EmpresasRentesegScreen.route){
            EmpresasRentesegScreen(navHostController = navController, isDarkTheme = isDarkTheme) {
            }
        }
        composable(AppScreens.FormularioPrimeroScreen.route){
            FormularioPrimeroScreen(navHostController = navController, isDarkTheme = isDarkTheme, icon = { icon },)
        }
        composable(AppScreens.FormularioSegundoScreen.route){
            FormularioSegundoScreen(navHostController = navController, isDarkTheme = isDarkTheme, icon = { icon },)
        }
        composable(AppScreens.FormularioPruebaScreen.route){
            FormularioPruebaScreen()
        }

        //donde se usa datetime picker y time picker
        composable(AppScreens.FormularioTerceroScreen.route){
            FormularioTerceroScreen(navHostController = navController, isDarkTheme = isDarkTheme) {
            }
        }

        composable(AppScreens.FormularioCuartoScreen.route){
            FormularioCuartoScreen(navHostController = navController, isDarkTheme =isDarkTheme ) {
            }
        }
    }
}
