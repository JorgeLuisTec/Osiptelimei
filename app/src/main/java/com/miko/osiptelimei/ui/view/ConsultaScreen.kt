package com.miko.osiptelimei.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.miko.osiptelimei.R
import com.miko.osiptelimei.ui.navigation.AppScreens
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsultaSreen(

    //Parámetro NavHostController
    navHostController: NavHostController,
    //Parametro Switch
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    ){

    //Variable para ver si falla el formulario
    val respuesta= "normal"

    //Variables y fx para el formulario Consulta IMEI
    val errorMessageImei = "Solo se permite 16 números"
    var imei by rememberSaveable { mutableStateOf("") }
    var isErrorImei by rememberSaveable { mutableStateOf(false) }
    val charLimitImei = 16
    fun validateImei(text2: String) {
        isErrorImei = text2.length > charLimitImei
    }

    //Variables para el drawer
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    data class IconItem(val icon: ImageVector, val description: String)
    val itemsOpciones = listOf(
        IconItem(Icons.Default.Info, "Preguntas frecuentes"),
        IconItem(Icons.Default.Search, "Empresas Renteseg"),
        IconItem(Icons.Default.List, "Formulario")
    )
    val selectedItem = remember { mutableStateOf(itemsOpciones[0]) }


    MaterialTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onBackground
            )
        ) {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet() {
                        Spacer(modifier = Modifier.height(12.dp))
                        itemsOpciones.forEach { item ->
                            NavigationDrawerItem(
                                //icon = { Icon(item, contentDescription = null) },
                                icon={ Icon(imageVector = item.icon, contentDescription = null)},
                                label = { Text(item.description) },
                                //selected = item == selectedItem.value,
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item

                                    if (selectedItem.value==itemsOpciones[1]){
                                        navHostController.navigate(route = AppScreens.EmpresasRentesegScreen.route)
                                    }
                                    if (selectedItem.value==itemsOpciones[2]){
                                        navHostController.navigate(route = AppScreens.FormularioPrincipalScreen.route)
                                    }
                                },
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                        }
                    }
                },
                content = {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 20.dp)
                    ) {
                        ElevatedCard(modifier=Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 15.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                //SWITCH
                                Switch(
                                    modifier = Modifier.semantics { contentDescription = "Demo with icon" },
                                    checked = isDarkTheme.value,
                                    onCheckedChange = { isDarkTheme.value = it },
                                    thumbContent = icon
                                )
                                //IMAGE COMO LOGO
                                ImageAsIcon(
                                    imageResId = R.drawable.solicitud,
                                    contentDescription = "My Image",
                                    size = 40.dp,
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable { /* Acción al hacer clic en el icono */ }
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),

                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(30.dp))

                                Image(
                                    painter = painterResource(id = R.drawable.logo_osiptel),
                                    contentDescription = "Logo",
                                    modifier = Modifier.size(200.dp)
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Card(
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Box(modifier = Modifier
                                            .fillMaxWidth()
                                            .background(color = MaterialTheme.colorScheme.primary)) {
                                            Text(
                                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                                text = "Este aplicativo permite consultar en línea el código IMEI ",
                                                color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(24.dp))

                                OutlinedTextField(
                                    value = imei,
                                    onValueChange = {
                                        if (it.all { it.isDigit() }
                                            //Para que aparezca rojo comentar esto
                                            && it.length<=charLimitImei
                                        ){
                                            imei = it
                                        }
                                        //text2 = it
                                        validateImei(imei)
                                    },
                                    label = { Text("Consultar IMEI") } ,
                                    placeholder = { Text("Consultar IEI ") },
                                    supportingText = {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = "Limite: ${imei.length}/$charLimitImei",
                                            textAlign = TextAlign.End,
                                        )
                                    },
                                    isError = isErrorImei,
                                    keyboardActions = KeyboardActions { validateImei(imei) },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .semantics {
                                            //if (isErrorImei) error(errorMessageImei)
                                        },
                                    )
                                Spacer(modifier = Modifier.height(24.dp))

                                Row(modifier = Modifier.fillMaxWidth()) {
                                    OutlinedCard(
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Box(modifier = Modifier
                                            .fillMaxWidth()

                                        ) {
                                            Row(modifier = Modifier.padding(10.dp)) {1
                                                val checkedState = remember { mutableStateOf(true) }

                                                Checkbox(
                                                    checked = checkedState.value,
                                                    onCheckedChange = { checkedState.value = it }
                                                )
                                                Text(
                                                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                                    text = "No soy un robot ",
                                                    //color = Color.White
                                                )
                                            }
                                        }
                                    }
                                }
                                Spacer(modifier = Modifier.height(30.dp))
                                Row() {
                                    ElevatedButton(
                                        modifier = Modifier.weight(1f),
                                        onClick = {
                                            if (respuesta =="normal"){
                                                navHostController.navigate(route = AppScreens.RespuestaNormalConsultaScreen.route)
                                            } else{
                                                navHostController.navigate(route = AppScreens.RespuestaAlertConsutaScreen.route)
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)) {
                                        /*
                                        Icon(
                                            imageVector = Icons.Filled.Send,
                                            contentDescription = "Favorite Icon",
                                            modifier = Modifier.align(Alignment.CenterVertically)
                                        )
                                         */
                                        Text(
                                            text = "Verificar",
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            style = TextStyle(fontSize = 18.sp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun ImageAsIcon(
    imageResId: Int,
    contentDescription: String,
    size: Dp,
    modifier: Modifier = Modifier
) {
    val image: Painter = painterResource(imageResId)

    Image(
        painter = image,
        contentDescription = contentDescription,
        modifier = modifier.size(size)

    )
}



//////


/*
@Preview
@Composable
fun preview(){


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun drawer(

    navHostController: NavHostController,
    //Parametro Switch
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,

){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// icons to mimic drawer destinations
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem = remember { mutableStateOf(items[0]) }

    val isDarkTheme = remember { mutableStateOf(false) }
    //Variable que regresa un icono de acuerdo a lo que manda
    val icon: (@Composable () -> Unit)? = if (isDarkTheme.value) {
        {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        null
    }

    var text2 by rememberSaveable { mutableStateOf("") }
    var isError2 by rememberSaveable { mutableStateOf(false) }
    val charLimit = 16

    fun validate2(text2: String) {
        isError2 = text2.length > charLimit
    }

    val respuesta= "normal"
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item

                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 20.dp)
            ) {
                ElevatedCard(modifier=Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //SWITCH
                        Switch(
                            modifier = Modifier.semantics { contentDescription = "Demo with icon" },
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            thumbContent = icon
                        )

                        //IMAGE COMO LOGO
                        ImageAsIcon(
                            imageResId = R.drawable.solicitud,
                            contentDescription = "My Image",
                            size = 40.dp,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable { /* Acción al hacer clic en el icono */ }
                        )
                    }


                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        Image(
                            painter = painterResource(id = R.drawable.logo_osiptel),
                            contentDescription = "Logo",
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.primary)) {
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                        text = "Este aplicativo permite consultar en línea el código IMEI ",
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedTextField(

                            value = text2,
                            onValueChange = {
                                if (it.all { it.isDigit() }
                                    //Para que aparezca rojo comentar esto
                                    && it.length<=charLimit
                                ){
                                    text2 = it
                                }
                                //text2 = it
                                validate2(text2)
                            },
                            label = { Text("Consultar IMEI") } ,
                            placeholder = { Text("Consultar IEI ") },
                            supportingText = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Limite: ${text2.length}/$charLimit",
                                    textAlign = TextAlign.End,
                                )
                            },
                            isError = isError2,
                            keyboardActions = KeyboardActions { validate2(text2) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .semantics {
                                    //if (isError2) error(errorMessage2)
                                },

                            )
                        Spacer(modifier = Modifier.height(24.dp))


                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedCard(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()

                                ) {
                                    Row(modifier = Modifier.padding(10.dp)) {1
                                        val checkedState = remember { mutableStateOf(true) }

                                        Checkbox(
                                            checked = checkedState.value,
                                            onCheckedChange = { checkedState.value = it }
                                        )
                                        Text(
                                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                            text = "No soy un robot ",
                                            //color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row() {
                            ElevatedButton(
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    if (respuesta =="normal"){
                                        navHostController.navigate(route = AppScreens.RespuestaNormalConsultaScreen.route)
                                    } else{
                                        navHostController.navigate(route = AppScreens.RespuestaAlertConsutaScreen.route)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)) {
                                /*
                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                 */
                                Text(
                                    text = "Verificar",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = TextStyle(fontSize = 18.sp)

                                )

                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                Spacer(Modifier.height(20.dp))
                Button(onClick = { scope.launch { drawerState.open() } }) {
                    Text("Click to open")
                }
            }
        }
    )

}
//////


 */







