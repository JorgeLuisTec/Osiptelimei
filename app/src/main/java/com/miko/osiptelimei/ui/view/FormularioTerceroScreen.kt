package com.miko.osiptelimei.ui.view

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.miko.osiptelimei.R
import com.miko.osiptelimei.ui.navigation.AppScreens
import com.miko.osiptelimei.ui.view.components.DatePickerExample
import com.miko.osiptelimei.ui.view.components.TimePickerExample


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioTerceroScreen(
    navHostController: NavHostController,
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    ){
    //variable para el scroll de la columna principal
    val scrollState = rememberScrollState()

    //Variables para los text field
    //val imei = remember { mutableStateOf("") }
    var imei by rememberSaveable { mutableStateOf("") }
    val apellidos = remember { mutableStateOf("") }
    val tipoDocumento = remember { mutableStateOf("") }
    val numeroDocumento = remember { mutableStateOf("") }
    val correo = remember { mutableStateOf("") }


    //para validacion de CodigoBloqueo
    var CodigoBloqueo by rememberSaveable { mutableStateOf("") }
    var isErrorCodigoBloqueo by rememberSaveable { mutableStateOf(false) }
    val charLimitCodigoBloqueo = 20
    fun validateCodigoBloqueo(CodigoBloqueo: String) {
        isErrorCodigoBloqueo = CodigoBloqueo.length > charLimitCodigoBloqueo
    }

    //variable para el dropdown MotivoConsulta
    var expandedMotivoConsulta by remember { mutableStateOf(false) }
    data class OpcionesMotivoConsulta(var id : String , val description: String)
    val itemsMotivoConsulta = listOf(
        OpcionesMotivoConsulta("11", "Mi equipo no figura como bloqueado, pese a que lo reporté en mi empresa."),
        OpcionesMotivoConsulta("12", "Mi equipo no figura como recuperado, pese a que lo reporté en mi empresa."),
        OpcionesMotivoConsulta("13", "Mi equipo figura como recuperado, pero no puedo usarlo."),
        OpcionesMotivoConsulta("14", "Mi equipo figura como bloqueado, sin haberlo reportado a la empresa operadora."),
        OpcionesMotivoConsulta("15", "Mi equipo figura como recuperado, sin haberlo haberlo solicitado a la empresa operadora."),
        OpcionesMotivoConsulta("99", "Otros (Detallar)"),
    )
    var selectedMotivoConsulta = remember { mutableStateOf(itemsMotivoConsulta[0]) }
    /////
    //validacion para número de teléfono contacto
    var NumCellContact by rememberSaveable { mutableStateOf("") }
    var isErrorNumCellContact by rememberSaveable { mutableStateOf(false) }
    val charLimitNumCellContact = 9
    fun validateNumCellContact(NumCell: String) {
        isErrorNumCellContact = NumCell.length > charLimitNumCellContact
    }

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

                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),

                        //horizontalArrangement = Arrangement.Center
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
                                .clickable { navHostController.navigate(AppScreens.FormularioPruebaScreen.route) }
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
                            text = "Datos complementarios",
                            fontWeight = FontWeight.Bold,
                            color = Color(android.graphics.Color.parseColor("#599BCC")),
                            style = TextStyle(fontSize = 18.sp),
                            )
                    }
                    LazyColumn(){

                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(scrollState),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        OutlinedTextField(
                            value = CodigoBloqueo,
                            onValueChange = {
                                if (it.all { it.isDigit() }
                                    //Para que aparezca rojo comentar esto
                                    && it.length<=charLimitCodigoBloqueo
                                ){
                                    CodigoBloqueo = it
                                }
                                //text2 = it
                                validateCodigoBloqueo(CodigoBloqueo)
                            },
                            label = { Text("Código de bloqueo *") } ,
                            placeholder = { Text("Código de bloqueo ") },
                            supportingText = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Limite: ${CodigoBloqueo.length}/$charLimitCodigoBloqueo",
                                    textAlign = TextAlign.End,
                                )
                            },
                            isError = isErrorCodigoBloqueo,
                            keyboardActions = KeyboardActions { validateCodigoBloqueo(imei) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .semantics {
                                    //if (isError2) error(errorMessage2)
                                },

                            )
                        Spacer(modifier = Modifier.height(10.dp))
                        ///dropdown MotivoConsulta
                        ///
                        ExposedDropdownMenuBox(
                            modifier=Modifier.fillMaxWidth(),
                            expanded = expandedMotivoConsulta,
                            onExpandedChange = { expandedMotivoConsulta = !expandedMotivoConsulta },
                        ) {
                            OutlinedTextField(
                                // The `menuAnchor` modifier must be passed to the text field for correctness.
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                readOnly = true,
                                value = selectedMotivoConsulta.value.description,
                                onValueChange = {},
                                label = { Text("Motivo Consulta *") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMotivoConsulta) },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            )
                            ExposedDropdownMenu(
                                expanded = expandedMotivoConsulta,
                                onDismissRequest = { expandedMotivoConsulta = false },
                            ) {
                                itemsMotivoConsulta.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption.description) },
                                        onClick = {
                                            selectedMotivoConsulta.value = selectionOption
                                            expandedMotivoConsulta = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }
                        //Spacer(modifier = Modifier.height(10.dp))
                        ///
                        Row(modifier = Modifier.fillMaxWidth()) {
                            DatePickerExample()
                        }

                        TimePickerExample()
                        ///

                        ///Validaciones para número de celular
                        OutlinedTextField(
                            value = NumCellContact,
                            onValueChange = {
                                if (it.all { it.isDigit() }
                                    //Para que aparezca rojo comentar esto
                                    && it.length<=charLimitNumCellContact
                                ){
                                    NumCellContact = it
                                }
                                //text2 = it
                                validateNumCellContact(NumCellContact)
                            },
                            label = { Text("Número de Móvil utilizado en el equipo *") } ,
                            placeholder = { Text("Número de Móvil utilizado en el equipo") },
                            supportingText = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Limite: ${NumCellContact.length}/$charLimitNumCellContact",
                                    textAlign = TextAlign.End,
                                )
                            },
                            isError = isErrorNumCellContact,
                            keyboardActions = KeyboardActions { validateNumCellContact(NumCellContact) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .semantics {
                                    //if (isError2) error(errorMessage2)
                                },
                            )
                        ///

                        Spacer(modifier = Modifier.height(30.dp))
                        Row() {
                            ElevatedButton(
                                modifier = Modifier.weight(1f),

                                onClick = {
                                    navHostController.navigate(AppScreens.FormularioCuartoScreen.route)
                                    if (

                                        imei.isBlank() ||
                                        apellidos.value.isBlank() ||
                                        tipoDocumento.value.isBlank() ||
                                        numeroDocumento.value.isBlank() ||
                                        correo.value.isBlank()
                                        // Mostrar un mensaje al usuario indicando que todos los campos son obligatorios
                                    ) {
                                    } else {
                                        // Procesar los datos del formulario
                                    }
                                          /*TODO*/

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









