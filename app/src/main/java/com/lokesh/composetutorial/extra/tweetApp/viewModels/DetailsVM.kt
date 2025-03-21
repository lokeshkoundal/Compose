package com.lokesh.composetutorial.extra.tweetApp.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokesh.composetutorial.extra.tweetApp.network.Tweet
import com.lokesh.composetutorial.extra.tweetApp.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(private val tweetRepository: TweetRepository,
                                    private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val tweets:StateFlow<List<Tweet>> = tweetRepository.tweets

    init {
        viewModelScope.launch {
            val category = savedStateHandle.get<String>("category") ?: "Android"
            tweetRepository.getTweets(category)
        }
    }


}