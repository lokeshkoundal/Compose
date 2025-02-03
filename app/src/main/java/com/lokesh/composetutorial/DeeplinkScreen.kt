package com.lokesh.composetutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeeplinkScreen(id: Int) {
    Box(Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {

        if(id == -1){
            Text("Please Use https://lokesh-compose.com/{id} Link to test this deeplink", fontSize = 26.sp)
        }else{
            Text("Deeplink Screen\nThe ID is : $id ", fontSize = 26.sp)
        }
    }
}