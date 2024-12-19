package com.lokesh.composetutorial.tweetApp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokesh.composetutorial.tweetApp.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoriesVM @Inject constructor(private val tweetRepository: TweetRepository) : ViewModel() {

    val categories : StateFlow<List<String>> = tweetRepository.categories

    init {
        viewModelScope.launch {
            tweetRepository.getCategories()
        }
    }

}