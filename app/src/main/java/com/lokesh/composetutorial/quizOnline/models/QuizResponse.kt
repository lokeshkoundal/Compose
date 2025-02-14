package com.lokesh.composetutorial.quizOnline.models

data class QuizResponse(
    val response_code: Int, // 0
    val results: List<Result>
) {
    data class Result(
        val category: String, // Entertainment: Books
        val correct_answer: String, // The Artful Dodger
        val difficulty: String, // medium
        val incorrect_answers: List<String>,
        val question: String, // By what nickname is Jack Dawkins known in the Charles Dickens novel, &#039;Oliver Twist&#039;?
        val type: String, // multiple
        val selectedAnswer: String?=null,
        val allAnswer : List<String>
    )
}