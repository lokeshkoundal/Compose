package com.lokesh.composetutorial

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.lokesh.composetutorial.tweetApp.viewModels.MainVM
import com.lokesh.composetutorial.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainVM: MainVM by viewModels()

    private val airplaneModeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            // Check if airplane mode is on or off
            val isAirplaneModeOn = intent?.getBooleanExtra("state", false) ?: return

            // Show a dialog based on the airplane mode state
//            showAirplaneModeDialog(isAirplaneModeOn)
            Log.d("AirplaneMode", "Airplane mode enabled: $isAirplaneModeOn")

            if(isAirplaneModeOn){
                mainVM.updateAirplaneModeStatus()
            }else{
                mainVM.dismissDialog()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

    registerReceiver(airplaneModeReceiver,
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))

        setContent {

            val snackbarHostState = remember { SnackbarHostState() }

            AppTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) }

                ) {paddingValues ->
                    RootNavigation(rememberNavController(),paddingValues,snackbarHostState)

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneModeReceiver)
    }
}

@Composable
fun ShowAirplaneModeDialog(viewModel: MainVM) {
    val dialogState by viewModel.showDialog.observeAsState(false)
    if(dialogState){
        AlertDialog(
            onDismissRequest = { }, // Handles outside clicks
            title = { Text(text = "Airplane Mode") },
            text = { Text(text = "Airplane Mode is on, Please turn it off") },
            confirmButton = {
                TextButton(onClick = { viewModel.dismissDialog() }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.dismissDialog() }) {
                    Text("Cancel")
                }
            })
    }
    else{

    }
    
}