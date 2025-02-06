package com.lokesh.composetutorial.quiz.model

import androidx.lifecycle.ViewModel
import com.lokesh.composetutorial.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizVM : ViewModel() {

    private val _score = MutableStateFlow(0)
    val score = _score.asStateFlow()

    private val _isLastQ = MutableStateFlow(false)
    val isLastQ  = _isLastQ.asStateFlow()

    private val _questions = MutableStateFlow(quizQuestions)
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
    var selectedAnswer: Answer? = null, // null means no answer selected
    val image: Int // Image resource ID (R.drawable.image_name)
)

val quizQuestions = listOf(
    Question(
        question = "What is the capital of Japan?",
        answers = listOf(Answer("Beijing"), Answer("Seoul"), Answer("Tokyo"), Answer("Bangkok")),
        correctAnswer = Answer("Tokyo"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which planet is known as the Red Planet?",
        answers = listOf(Answer("Earth"), Answer("Mars"), Answer("Jupiter"), Answer("Venus")),
        correctAnswer = Answer("Mars"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Who wrote 'Hamlet'?",
        answers = listOf(Answer("William Wordsworth"), Answer("Charles Dickens"), Answer("William Shakespeare"), Answer("Jane Austen")),
        correctAnswer = Answer("William Shakespeare"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "What is the largest mammal in the world?",
        answers = listOf(Answer("African Elephant"), Answer("Blue Whale"), Answer("Giraffe"), Answer("Great White Shark")),
        correctAnswer = Answer("Blue Whale"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which is the smallest continent by land area?",
        answers = listOf(Answer("Europe"), Answer("Australia"), Answer("Antarctica"), Answer("South America")),
        correctAnswer = Answer("Australia"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "What is the currency of the United Kingdom?",
        answers = listOf(Answer("Euro"), Answer("Pound Sterling"), Answer("Dollar"), Answer("Yen")),
        correctAnswer = Answer("Pound Sterling"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which ocean is the largest?",
        answers = listOf(Answer("Atlantic Ocean"), Answer("Indian Ocean"), Answer("Pacific Ocean"), Answer("Arctic Ocean")),
        correctAnswer = Answer("Pacific Ocean"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Who developed the theory of relativity?",
        answers = listOf(Answer("Isaac Newton"), Answer("Nikola Tesla"), Answer("Albert Einstein"), Answer("Galileo Galilei")),
        correctAnswer = Answer("Albert Einstein"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "What is the main ingredient in guacamole?",
        answers = listOf(Answer("Tomato"), Answer("Avocado"), Answer("Cucumber"), Answer("Lettuce")),
        correctAnswer = Answer("Avocado"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which country is famous for the Great Wall?",
        answers = listOf(Answer("India"), Answer("China"), Answer("Japan"), Answer("Russia")),
        correctAnswer = Answer("China"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which gas do plants absorb from the atmosphere?",
        answers = listOf(Answer("Oxygen"), Answer("Nitrogen"), Answer("Carbon Dioxide"), Answer("Hydrogen")),
        correctAnswer = Answer("Carbon Dioxide"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which of the following is a primary color?",
        answers = listOf(Answer("Green"), Answer("Yellow"), Answer("Blue"), Answer("Purple")),
        correctAnswer = Answer("Blue"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "What is the longest river in the world?",
        answers = listOf(Answer("Amazon River"), Answer("Nile River"), Answer("Yangtze River"), Answer("Mississippi River")),
        correctAnswer = Answer("Nile River"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "How many continents are there on Earth?",
        answers = listOf(Answer("5"), Answer("6"), Answer("7"), Answer("8")),
        correctAnswer = Answer("7"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "What is the chemical symbol for gold?",
        answers = listOf(Answer("Au"), Answer("Ag"), Answer("Fe"), Answer("Hg")),
        correctAnswer = Answer("Au"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which year did the Titanic sink?",
        answers = listOf(Answer("1905"), Answer("1912"), Answer("1923"), Answer("1898")),
        correctAnswer = Answer("1912"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "What is the tallest mountain in the world?",
        answers = listOf(Answer("Mount Kilimanjaro"), Answer("Mount Everest"), Answer("K2"), Answer("Denali")),
        correctAnswer = Answer("Mount Everest"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Which animal is known as the 'King of the Jungle'?",
        answers = listOf(Answer("Tiger"), Answer("Elephant"), Answer("Lion"), Answer("Cheetah")),
        correctAnswer = Answer("Lion"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "What is the freezing point of water in Celsius?",
        answers = listOf(Answer("0°C"), Answer("32°C"), Answer("-10°C"), Answer("100°C")),
        correctAnswer = Answer("0°C"),
        image = R.drawable.ic_tweet
    ),
    Question(
        question = "Who painted the Mona Lisa?",
        answers = listOf(Answer("Vincent van Gogh"), Answer("Pablo Picasso"), Answer("Leonardo da Vinci"), Answer("Claude Monet")),
        correctAnswer = Answer("Leonardo da Vinci"),
        image = R.drawable.ic_tweet
    )
)
