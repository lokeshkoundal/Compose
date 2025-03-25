package com.lokesh.composetutorial.quizOnline.viewModel

import androidx.lifecycle.ViewModel
import com.lokesh.composetutorial.quizOnline.database.QuizResult
import com.lokesh.composetutorial.quizOnline.repository.QuizHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizHistoryVM @Inject constructor(private val quizHistoryRepository: QuizHistoryRepository) : ViewModel() {

    var quizHistoryList = MutableStateFlow<List<QuizResult>>(emptyList())
        private set

    var isLoading = MutableStateFlow(true)
        private set

    fun emptyQuizHistory(){
        CoroutineScope(Dispatchers.IO).launch {
            quizHistoryRepository.deleteAllQuizResults()
            quizHistoryList.emit(emptyList())
        }
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.emit(true)
            quizHistoryList.emit(quizHistoryRepository.getAllQuizResults())
            isLoading.emit(false)
        }
    }
}