package com.lokesh.composetutorial.extra.mediaplayer

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MediaVM @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player
) : ViewModel() {

    private val videoURis  = savedStateHandle.getStateFlow("videoUris", emptyList<Uri>())
    val videoItems  =  videoURis.map {uris->

        uris.map { uri->
            VideoItem(
                contentUri = uri,
                mediaItem = MediaItem.fromUri(uri),
                name =  "No Name thi is file"

            )
        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        player.prepare()
    }

    fun addVideoUri(uri : Uri){
        savedStateHandle["videoURis"] = videoURis.value + uri
        player.addMediaItem(MediaItem.fromUri(uri))
    }

    fun playVideo(uri : Uri){
        player.setMediaItem(
            videoItems.value.find { it.contentUri == uri }?.mediaItem ?: return

        )
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}