package com.miko.osiptelimei.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.miko.osiptelimei.R
import com.miko.osiptelimei.ui.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioPrimeroScreen(
    navHostController: NavHostController,
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    ){
    //Variables para los text field
    val nombre = remember { mutableStateOf("") }
    val apellidos = remember { mutableStateOf("") }
    val tipoDocumento = remember { mutableStateOf("") }
    val numeroDocumento = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }

    /*
    // Variables para color de los formularios
    val colorNombre = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorApellidos = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorTipoDocumento = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorNumeroDocumento = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorCorreo = remember { mutableStateOf(Color.Red) }
     */

    //variable para el scroll
    val scrollState = rememberScrollState()

    MaterialTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.onBackground
            )

        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 20.dp)
            ) {
                ElevatedCard(modifier= Modifier.fillMaxWidth()) {

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
                        //Image como logo
                        ImageAsIcon(
                            imageResId = R.drawable.solicitud,
                            contentDescription = "My Image",
                            size = 40.dp,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable { /* Acción al hacer clic en el icono */ }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        //verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp, start = 30.dp, end = 30.dp)
                                .wrapContentSize(align = Alignment.Center),
                            text = "Ingresa tus datos personales para el formulario de consulta",

                            fontWeight = FontWeight.Bold,
                            color = Color(android.graphics.Color.parseColor("#599BCC")),
                            style = TextStyle(fontSize = 18.sp),
                            )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        Image(
                            painter = painterResource(id = R.drawable.contract),
                            contentDescription = "Logo",
                            modifier = Modifier.size(100.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = nombre.value,
                            onValueChange = { nombre.value = it },
                            label = { Text("Nombre *") } ,
                            placeholder = { Text("Nombre * ") },
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = apellidos.value,
                            onValueChange = { apellidos.value = it },
                            label = { Text("Apellidos *") } ,
                            placeholder = { Text("Apellidos *") },
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = tipoDocumento.value,
                            onValueChange = { tipoDocumento.value = it },
                            label = { Text("Tipo de documento legal *") } ,
                            placeholder = { Text("Tipo de documento legal *") },
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = numeroDocumento.value,
                            onValueChange = { numeroDocumento.value = it },
                            label = { Text("Numero de documento legal *") } ,
                            placeholder = { Text("Numero de documento legal *") },
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = correo.value,
                            onValueChange = { correo.value = it },
                            label = { Text("Correo electrónico *") } ,
                            placeholder = { Text("Correo electrónico *") },
                        )

                        Spacer(modifier = Modifier.height(30.dp))
                        Row() {

                            ElevatedButton(
                                modifier = Modifier.weight(1f),

                                onClick = {
                                    navHostController.navigate(AppScreens.FormularioSegundoScreen.route)
                                    /*
                                    //PARA VALIDAR
                                    if (nombre.value.isBlank() ||
                                        apellidos.value.isBlank() ||
                                        tipoDocumento.value.isBlank() ||
                                        numeroDocumento.value.isBlank() ||
                                        correo.value.isBlank()
                                        // Mostrar un mensaje al usuario indicando que todos los campos son obligatorios
                                    ) {

                                    } else {
                                        // Procesar los datos del formulario
                                        navHostController.navigate(AppScreens.FormularioSegundoScreen.route)
                                    }

                                     */

                                          },
                                colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)) {

                                Icon(
                                    imageVector = Icons.Filled.Send,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "Continuar",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = TextStyle(fontSize = 18.sp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



