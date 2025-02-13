package com.lokesh.composetutorial.quizOnline.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lokesh.composetutorial.quizOnline.viewModel.OnlineQuizVM

@Composable
fun OnlineQuizScreen(categoryId: Int) {
    val onlineQuizVM : OnlineQuizVM = hiltViewModel()
    val isLoading = onlineQuizVM.isLoading.collectAsState()

}