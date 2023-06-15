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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun RespuestaNormalConsultaScreen(
    //Parámetro NavHostController
    navHostController: NavHostController,

    //Parámetro DarkMode
    isDarkTheme: MutableState<Boolean>,
    icon: @Composable() (() -> Unit)?,
    ){
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

                        horizontalArrangement = Arrangement.End
                        //horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        /*
                        Switch(
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
                        )
                         */

                        //SWITCH
                        Switch(
                            modifier = Modifier.semantics { contentDescription = "Demo with icon" },
                            checked = isDarkTheme.value,
                            onCheckedChange = { isDarkTheme.value = it },
                            thumbContent = icon
                        )

                        /*

                        //Image como logo
                        ImageAsIcon(
                            imageResId = R.drawable.solicitud,
                            contentDescription = "My Image",
                            size = 40.dp,
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable { /* Acción al hacer clic en el icono */ }
                        )

                         */
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(30.dp))

                        Image(
                            painter = painterResource(id = R.drawable.search),
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
                                        modifier = Modifier.padding(top = 10.dp, bottom = 15.dp, start = 16.dp, end = 16.dp),
                                        text = "La respuesta a su consulta es :",

                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        style = TextStyle(fontSize = 20.sp)

                                    )

                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))


                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlinedCard(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()

                                ) {
                                    Column() {

                                        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Start) {
                                            Text(
                                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                                text ="El número :",
                                                //color = MaterialTheme.colorScheme.onPrimary
                                            )
                                        }

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center

                                        ) {
                                            Text(
                                                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                                                text = "1234567891011",

                                                fontWeight = FontWeight.Bold,
                                                //color = Color(android.graphics.Color.parseColor("#599BCC")),
                                                //color = Color(android.graphics.Color.parseColor("#599BCC")),
                                                style = TextStyle(fontSize = 23.sp)
                                            )
                                        }

                                        Text(
                                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),

                                            text = "El IMEI no se encuentra asociado a ningún terminal reportado",
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))

                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Row() {
                            ElevatedButton(
                                modifier = Modifier.weight(1f),
                                onClick = {navHostController.navigate(AppScreens.ConsultaScreen.route)  },
                                colors = ButtonDefaults.buttonColors( MaterialTheme.colorScheme.primary)
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Favorite Icon",
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )

                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = "Regresar",
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