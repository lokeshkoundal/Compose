package com.lokesh.composetutorial.quiz.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuizVM @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var  questionList = emptyList<Question>()

    init {
         val gson  = Gson()
         val inputStream = application.assets.open("Questions.json")

         val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }

        val listType = object : TypeToken<List<Question>>() {}.type
        val result: List<Question> = gson.fromJson(jsonString, listType)

        questionList = result
    }

    private val _score = MutableStateFlow(-1)
    val score = _score.asStateFlow()

    private val _isLastQ = MutableStateFlow(false)
    val isLastQ  = _isLastQ.asStateFlow()

    private val _questions = MutableStateFlow(questionList)
    val questions  = _questions.asStateFlow()

    private var _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex = _currentQuestionIndex.asStateFlow()

    fun calculateScore() {
        var score = 0
        questions.value.forEach { question ->
            if (question.selectedAnswer == question.correctAnswer) {
                score++
            }
        }
        _score.value = score

    }

    fun nextQuestion(){
        if(currentQuestionIndex.value < questions.value.size-1){
            _currentQuestionIndex.value++

        }
        if(currentQuestionIndex.value == questions.value.size-1){
            _isLastQ.value = true

        }
    }

    fun previousQuestion(){
        if(currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value--
            _isLastQ.value = false
        }
    }

    fun answerSelected(questionIndex: Int, answer: Answer) {
        _questions.value = _questions.value.toMutableList().apply {
            this[questionIndex] = this[questionIndex].copy(selectedAnswer = answer)
        }
    }
}

data class Answer(val text: String)
data class Question(
    val question: String,
    val answers: List<Answer>,
    val correctAnswer: Answer,
    var selectedAnswer: Answer? = null,
    val image: String
)