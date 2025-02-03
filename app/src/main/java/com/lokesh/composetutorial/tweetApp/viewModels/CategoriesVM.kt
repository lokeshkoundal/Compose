package com.lokesh.composetutorial.tweetApp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokesh.composetutorial.tweetApp.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


@HiltViewModel
class CategoriesVM @Inject constructor(private val tweetRepository: TweetRepository) : ViewModel() {

    var categories : StateFlow<List<String>> = tweetRepository.categories
    var isLoading  = MutableStateFlow(true)
        private set
    init {
        viewModelScope.launch {
            try{
                isLoading.emit(true)
                withTimeout(4000){
                    tweetRepository.getCategories()
                }
                isLoading.emit(false)

            }catch (e: TimeoutCancellationException){
                categories = MutableStateFlow(emptyList())
                isLoading.emit(false)

            }

        }
    }

}