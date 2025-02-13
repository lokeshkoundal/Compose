package com.lokesh.composetutorial.mediaplayer

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.ui.PlayerView

@Composable
fun MediaScreen(modifier: Modifier = Modifier) {
    val mediaVM : MediaVM = hiltViewModel()
    val videoItems = mediaVM.videoItems.collectAsState()

    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let(mediaVM::addVideoUri)
        }
    )

    var lifecycle by remember{
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        AndroidView(
            factory = {context->
                PlayerView(context).also {
                    it.player = mediaVM.player
                }
            },

            update = {
                when(lifecycle){
                    Lifecycle.Event.ON_PAUSE->{
                        it.onPause()
                        it.player?.pause()
                    }
                    Lifecycle.Event.ON_RESUME->{
                        it.onResume()
                    }
                    else-> Unit
                }
                },
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(16/9f)
        )

        Spacer(Modifier.height(8.dp))

        IconButton(onClick = {
            selectVideoLauncher.launch("video/mp4") }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()){

            items(items = videoItems.value){item->
                Text(text = item.name,
                    color = Color.Black,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            mediaVM.playVideo(item.contentUri)
                        }
                        .padding(16.dp))

            }
        }


    }

}