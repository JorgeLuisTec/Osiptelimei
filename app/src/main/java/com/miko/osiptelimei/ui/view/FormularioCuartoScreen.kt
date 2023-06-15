package com.miko.osiptelimei.ui.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.miko.osiptelimei.R
import com.miko.osiptelimei.ui.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioCuartoScreen(
    navHostController: NavHostController,
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    ){

    //variables para scroll de column
    val scrollState = rememberScrollState()

    //variables para el campo comentario
    var comentarios by rememberSaveable { mutableStateOf("") }

    //Variable para Capturar archivo
    val result = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        result.value = it
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

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(scrollState),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.primary)) {
                                    Text(
                                        modifier = Modifier.padding(top = 10.dp, bottom = 15.dp, start = 16.dp, end = 16.dp),
                                        text = "Finalizar Reporte: ",
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = TextStyle(fontSize = 20.sp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Image(
                            painter = painterResource(id = R.drawable.smartphone),
                            contentDescription = "Logo",
                            modifier = Modifier.size(150.dp)
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        //OutLinedTextField
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = comentarios,
                            onValueChange = { comentarios = it },
                            label = { Text("Comentarios *") } ,
                            placeholder = { Text("Comentarios * ") },
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedCard(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                ) {
                                    Column() {
                                        Text(
                                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                            text = "Adjuntar Archivo (PDF o imagenes)",
                                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                                            textAlign = TextAlign.Justify
                                        )
                                        Row() {
                                            ElevatedButton(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .padding(16.dp),
                                                //onClick = { pickFileLauncher.launch("image/*") },
                                                onClick = { launcher.launch("image/*") },
                                                colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Filled.Info,
                                                    contentDescription = "Icono Info",
                                                    modifier = Modifier.align(Alignment.CenterVertically)
                                                )
                                                Text(
                                                    modifier = Modifier.padding(start = 10.dp),
                                                    text = "Buscar archivo",
                                                    color = MaterialTheme.colorScheme.onPrimary,
                                                    style = TextStyle(fontSize = 18.sp)
                                                )
                                            }

                                        }
                                        Column() {
                                            result.value?.let { uri ->
                                                Text("Archivo seleccionado: $uri")
                                            }
                                            IconButton(onClick = { result.value=null }) {
                                                Icon(Icons.Outlined.Lock, contentDescription = "Localized description")
                                            }

                                        }
                                        /*
                                        fileUri?.let { uri ->
                                            TextButton(onClick = { }) {
                                                Text(uri.toString())
                                            }
                                        }

                                         */
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row() {
                            ElevatedButton(
                                modifier = Modifier.weight(1f),
                                onClick = {  },
                                colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )

                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "Finalizar",
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