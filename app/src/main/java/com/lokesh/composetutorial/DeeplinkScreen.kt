package com.lokesh.composetutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DeeplinkScreen(id: Int) {
    Box(Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.verticalScroll(rememberScrollState())) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.cnbcfm.com/api/v1/image/108095912-1738362749191-gettyimages-2196367611-AFP_36WV3GE.jpeg?v=1738362919&w=1920&h=1080")
                    .crossfade(true)
                    .crossfade(250)
                    .build(),
                contentDescription = "",
                placeholder = rememberVectorPainter(image = Icons.Default.Refresh),
                error = rememberVectorPainter(image = Icons.Default.Info)
            )

            if(id == -1){
                Text("Please Use https://lokesh-compose.com/{id} Link to test this deeplink",
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center)
            }else{
                Text("Deeplink Screen\nThe ID is : $id ",
                    fontSize = 26.sp,
                    textAlign = TextAlign.Center)
            }
        }


    }
}