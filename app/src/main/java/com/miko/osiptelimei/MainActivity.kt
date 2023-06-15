package com.miko.osiptelimei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.miko.osiptelimei.ui.navigation.AppNavigation
import com.miko.osiptelimei.ui.theme.OsiptelimeiTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //Variables para dark theme
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

            //Variables para drawer
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            // icons to mimic drawer destinations
            val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
            val selectedItem = remember { mutableStateOf(items[0]) }


            OsiptelimeiTheme (darkTheme = isDarkTheme.value) {
                // A surface container using the 'background' color from the theme
                /*
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
                 */

                AppNavigation(
                    //Scwitch
                    isDarkTheme = isDarkTheme,
                    icon = icon,
                )
            }
        }
    }
}

