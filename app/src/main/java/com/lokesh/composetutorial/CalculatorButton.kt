package com.lokesh.composetutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier,
    onClick : ()->Unit
){
    Box(modifier = modifier)
}