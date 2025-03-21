package com.lokesh.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.lokesh.composetutorial.extra.tweetApp.viewModels.MainVM
import com.lokesh.composetutorial.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainVM: MainVM by viewModels()
//
//    private val airplaneModeReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            // Check if airplane mode is on or off
//            val isAirplaneModeOn = intent?.getBooleanExtra("state", false) ?: return
//
//            // Show a dialog based on the airplane mode state
////            showAirplaneModeDialog(isAirplaneModeOn)
//            Log.d("AirplaneMode", "Airplane mode enabled: $isAirplaneModeOn")
//
//            if(isAirplaneModeOn){
//                mainVM.updateAirplaneModeStatus()
//            }else{
//                mainVM.dismissDialog()
//            }
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

//    registerReceiver(airplaneModeReceiver,
//        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))

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
//        unregisterReceiver(airplaneModeReceiver)
    }
}

//@Composable
//fun ShowAirplaneModeDialog(viewModel: MainVM) {
//    val dialogState by viewModel.showDialog.observeAsState(false)
//    if(dialogState){
//        AlertDialog(
//            onDismissRequest = { }, // Handles outside clicks
//            title = { Text(text = "Airplane Mode") },
//            text = { Text(text = "Airplane Mode is on, Please turn it off") },
//            confirmButton = {
//                TextButton(onClick = { viewModel.dismissDialog() }) {
//                    Text("OK")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = { viewModel.dismissDialog() }) {
//                    Text("Cancel")
//                }
//            })
//    }
//    else{
//
//    }
//
//}