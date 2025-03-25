package com.lokesh.composetutorial.quizOnline.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lokesh.composetutorial.quizOnline.database.QuizResult
import com.lokesh.composetutorial.quizOnline.models.QuizResponse.Result
import com.lokesh.composetutorial.quizOnline.repository.QuizHistoryRepository
import com.lokesh.composetutorial.quizOnline.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnlineQuizVM @Inject constructor(private val quizRepository: QuizRepository,
                                       private val quizHistoryRepository: QuizHistoryRepository
) : ViewModel() {


    var quizQuestions  = MutableStateFlow<List<Result>>(emptyList())
        private set

    var isLoading  = MutableStateFlow(true)
        private set

    var currentQuestionIndex =  MutableStateFlow(0)
        private set

    var isLastQ =  MutableStateFlow(false)
        private set

    var isQuizFinished = MutableStateFlow(false)
        private set

    var scored = MutableStateFlow(0)
        private set

    private var currentQuizCategoryId = MutableStateFlow(-1)



    fun getQuestions(categoryId: Int) {
        viewModelScope.launch {
            isLoading.emit(true)
            try {
                val res = if(categoryId==-1){
                    quizRepository.getRandomQuestions()
                }else{
                    quizRepository.getQuestions(categoryId)
                }
                res.let {
                    val updatedList = it.body()?.results?.map { result ->
                        result.copy(allAnswer = (result.incorrect_answers + result.correct_answer).shuffled())
                    }
                    if (updatedList != null) {
                        quizQuestions.emit(updatedList)
                        currentQuizCategoryId = MutableStateFlow(categoryId)

                    }
                }

            }catch (e:Exception){
                Log.e("Quiz", "Error fetching quiz: ${e.message}")
                quizQuestions.emit(emptyList())
            }

            isLoading.emit(false)

        }

    }


    fun calculateScoreAndSaveToDB() {
        var score = 0
        quizQuestions.value.forEach { question ->
            if (question.selectedAnswer == question.correct_answer) {
                score++
            }
        }
        scored.value = score
        isQuizFinished.value = true

        viewModelScope.launch {
            quizHistoryRepository.insert(
                QuizResult(
                    correctAnswers = score,
                    wrongAnswers = quizQuestions.value.size - score,
                    categoryId = currentQuizCategoryId.value
                )
            )
        }


    }

    fun dismissDialog() {
        isQuizFinished.value = false
    }

    fun nextQuestion(){
        if(currentQuestionIndex.value < quizQuestions.value.size-1){
            currentQuestionIndex.value++

        }
        if(currentQuestionIndex.value == quizQuestions.value.size-1){
            isLastQ.value = true

        }
    }

    fun previousQuestion(){
        if(currentQuestionIndex.value > 0) {
            currentQuestionIndex.value--

            if(isLastQ.value)
                isLastQ.value = false
        }
    }

    fun answerSelected(questionIndex: Int, answer: String) {
        quizQuestions.value = quizQuestions.value.toMutableList().apply {
            this[questionIndex] = this[questionIndex].copy(selectedAnswer = answer)
        }
    }


}