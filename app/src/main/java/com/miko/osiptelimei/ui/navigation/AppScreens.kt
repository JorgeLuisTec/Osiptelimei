package com.miko.osiptelimei.ui.navigation

sealed class AppScreens (val route:String){
    object IncioScreen : AppScreens("incio_screen")
    object ConsultaScreen: AppScreens("consulta_screen")
    object RespuestaNormalConsultaScreen: AppScreens("respuesta_normal_consulta")
    object RespuestaAlertConsutaScreen: AppScreens("respuesta_alert_consulta")
    //object HomeScreen:AppScreens("home_screen")
    object FormularioPrincipalScreen:AppScreens("formulario_principal")
    object EmpresasRentesegScreen:AppScreens("empresas_renteseg")
    object FormularioPrimeroScreen:AppScreens("formulario_primero")
    object FormularioSegundoScreen:AppScreens("formulario_segundo")
    object FormularioTerceroScreen:AppScreens("formulario_tercero")
    object FormularioCuartoScreen : AppScreens("formulario_cuarto")


    //Es de prueba para enseñar
    object FormularioPruebaScreen:AppScreens("formulario_prueba")
}
