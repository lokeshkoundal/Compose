package com.lokesh.composetutorial.quizOnline.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokesh.composetutorial.quizOnline.models.QuizResponse
import com.lokesh.composetutorial.quizOnline.repository.QuizRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnlineQuizVM @Inject constructor(val quizRepository: QuizRepository) : ViewModel() {

    var quizQuestions  = MutableStateFlow<List<QuizResponse.Result>>(emptyList())
        private set
    var isLoading  = MutableStateFlow(true)
        private set

   fun getQuestions(categoryId: Int) {
        viewModelScope.launch {
            isLoading.value = true
            val res = quizRepository.getQuestions(categoryId)
//            quizQuestions = res.body().results
            isLoading.value = false

        }

    }

}