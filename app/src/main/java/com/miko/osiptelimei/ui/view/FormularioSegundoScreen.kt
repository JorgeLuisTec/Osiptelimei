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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.miko.osiptelimei.R
import com.miko.osiptelimei.ui.navigation.AppScreens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioSegundoScreen(
    navHostController: NavHostController,
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    ){
    //Variables para los text field
    //val imei = remember { mutableStateOf("") }
    var imei by rememberSaveable { mutableStateOf("") }


    //para validacion de imei
    var isError by rememberSaveable { mutableStateOf(false) }
    val charLimitImei = 16
    fun validateImei(text2: String) {
        isError = imei.length > charLimitImei
    }


    // Variables para color de los formularios
    val colorNombre = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorApellidos = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorTipoDocumento = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorNumeroDocumento = remember { mutableStateOf(Color.Red) } // replace with a default color
    val colorCorreo = remember { mutableStateOf(Color.Red) }

    //variable para el scroll de la columna principal
    val scrollState = rememberScrollState()


    //variable para el dropdown MarcaMovil
    var expandedMarcaMovil by remember { mutableStateOf(false) }
    data class OpcionesMarcaMovil(var name: String, val description: String)
    val itemsMarcaMovil = listOf(
        OpcionesMarcaMovil("Redmi", "Descripción1"),
        OpcionesMarcaMovil("Google", "Descripción2"),
        OpcionesMarcaMovil("Landi", "Descripción3"),
    )
    var selectedOptionMarcaMovil = remember { mutableStateOf(itemsMarcaMovil[0]) }


    //variable para el dropdown ModeloMovil
    var expandedModelMovil by remember { mutableStateOf(false) }
    data class OpcionesModelMovil(var name: String, val description: String)
    val itemsModelMovil = listOf(
        OpcionesModelMovil("M2006C3LC", "Descripción1"),
        OpcionesModelMovil("M2007J17C", "Descripción2"),
        OpcionesModelMovil("M2007J22C", "Descripción3"),
    )
    var selectedOptionModelMovil = remember { mutableStateOf(itemsModelMovil[0]) }


    //variable para el dropdown ConcesionarioMovil
    var expandedConcesionarioMovil by remember { mutableStateOf(false) }
    data class OpcionesConcesionarioMovil(var name: String, val description: String)
    val itemsConcesionarioMovil = listOf(
        OpcionesConcesionarioMovil("Entel S.A", "Descripción1"),
        OpcionesConcesionarioMovil("Claro S.A", "Descripción2"),
        OpcionesConcesionarioMovil("Bitel", "Descripción3"),
    )
    var selectedConcesionarioMovil = remember { mutableStateOf(itemsConcesionarioMovil[0]) }


    //validacion para numero de celular
    var NumCell by rememberSaveable { mutableStateOf("") }
    var isErrorNumCell by rememberSaveable { mutableStateOf(false) }
    val charLimitNumCell = 9
    fun validateNumCell(text2: String) {
        isErrorNumCell = NumCell.length > charLimitNumCell
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
                            text = "Ingresa los datos del equipo a reportar",
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


                        Image(
                            painter = painterResource(id = R.drawable.smartphone),
                            contentDescription = "Logo",
                            modifier = Modifier.size(100.dp)
                        )
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
                            label = { Text("IMEI *") } ,
                            placeholder = { Text("IMEI") },
                            supportingText = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Limite: ${imei.length}/$charLimitImei",
                                    textAlign = TextAlign.End,
                                )
                            },
                            isError = isError,
                            keyboardActions = KeyboardActions { validateImei(imei) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .semantics {
                                    //if (isError2) error(errorMessage2)
                                },

                            )
                        Spacer(modifier = Modifier.height(10.dp))
                        //dropdown MarcaMovil
                        ///
                        ExposedDropdownMenuBox(
                            modifier=Modifier.fillMaxWidth(),
                            expanded = expandedMarcaMovil,
                            onExpandedChange = { expandedMarcaMovil = !expandedMarcaMovil },
                        ) {
                            OutlinedTextField(
                                // The `menuAnchor` modifier must be passed to the text field for correctness.
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                readOnly = true,
                                value = selectedOptionMarcaMovil.value.name,
                                onValueChange = {},
                                label = { Text("Marca móvil *") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMarcaMovil) },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            )
                            ExposedDropdownMenu(
                                expanded = expandedMarcaMovil,
                                onDismissRequest = { expandedMarcaMovil = false },
                            ) {
                                itemsMarcaMovil.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption.name) },
                                        onClick = {
                                            selectedOptionMarcaMovil.value = selectionOption
                                            expandedMarcaMovil = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }
                        ///
                        ///Drop down Modelo móvil
                        ExposedDropdownMenuBox(
                            modifier=Modifier.fillMaxWidth(),
                            expanded = expandedModelMovil,
                            onExpandedChange = { expandedModelMovil = !expandedModelMovil },
                        ) {
                            OutlinedTextField(
                                // The `menuAnchor` modifier must be passed to the text field for correctness.
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                readOnly = true,
                                value = selectedOptionModelMovil.value.name,
                                onValueChange = {},
                                label = { Text("Modelo móvil *") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedModelMovil) },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            )
                            ExposedDropdownMenu(
                                expanded = expandedModelMovil,
                                onDismissRequest = { expandedModelMovil = false },
                            ) {
                                itemsModelMovil.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption.name) },
                                        onClick = {
                                            selectedOptionModelMovil.value = selectionOption
                                            expandedModelMovil = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }
                        ///
                        ///DropDown Concesionario movil
                        ExposedDropdownMenuBox(
                            modifier=Modifier.fillMaxWidth(),
                            expanded = expandedConcesionarioMovil,
                            onExpandedChange = { expandedConcesionarioMovil = !expandedConcesionarioMovil },
                        ) {
                            OutlinedTextField(
                                // The `menuAnchor` modifier must be passed to the text field for correctness.
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                readOnly = true,
                                value = selectedConcesionarioMovil.value.name,
                                onValueChange = {},
                                label = { Text("Concesionario movil *") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedConcesionarioMovil) },
                                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                            )
                            ExposedDropdownMenu(
                                expanded = expandedConcesionarioMovil,
                                onDismissRequest = { expandedConcesionarioMovil = false },
                            ) {
                                itemsConcesionarioMovil.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption.name) },
                                        onClick = {
                                            selectedConcesionarioMovil.value = selectionOption
                                            expandedConcesionarioMovil = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }
                        ///
                        ///
                        //Spacer(modifier = Modifier.height(10.dp))

                        ///Validaciones para número de celular
                        OutlinedTextField(
                            value = NumCell,
                            onValueChange = {
                                if (it.all { it.isDigit() }
                                    //Para que aparezca rojo comentar esto
                                    && it.length<=charLimitNumCell
                                ){
                                    NumCell = it
                                }
                                //text2 = it
                                validateNumCell(NumCell)
                            },
                            label = { Text("Número de Móvil utilizado en el equipo *") } ,
                            placeholder = { Text("Número de Móvil utilizado en el equipo") },
                            supportingText = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Limite: ${NumCell.length}/$charLimitNumCell",
                                    textAlign = TextAlign.End,
                                )
                            },
                            isError = isErrorNumCell,
                            keyboardActions = KeyboardActions { validateNumCell(NumCell) },
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
                                          navHostController.navigate(AppScreens.FormularioTerceroScreen.route)
                                    /*
                                    if (imei.isBlank() ||
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


/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Example() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Label") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }

}

 */









