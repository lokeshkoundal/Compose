package com.lokesh.composetutorial.calculator

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lokesh.composetutorial.calculator.composeElements.CalculatorButton
import com.lokesh.composetutorial.calculator.model.CalculatorAction
import com.lokesh.composetutorial.calculator.model.CalculatorState
import com.lokesh.composetutorial.calculator.model.CalculatorViewModel
import com.lokesh.composetutorial.calculator.theme.ComposeTutorialTheme
import com.lokesh.composetutorial.calculator.theme.LightGray
import com.lokesh.composetutorial.calculator.theme.MediumGray
import com.lokesh.composetutorial.calculator.theme.Orange

@Composable
fun CalculatorScreen(){
    val calculatorViewModel = viewModel<CalculatorViewModel>()
            val state = calculatorViewModel.state
            val buttonSpacing = 8.dp
            Calculator(
                state = state,
                onAction = calculatorViewModel::onAction,
                buttonSpacing = buttonSpacing,
                modifier = Modifier.fillMaxSize()
                    .background(MediumGray)
                    .padding(16.dp))
}
@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {

    ComposeTutorialTheme {

        Box(modifier) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                AnimatedContent(  modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                    targetState = state,
                   transitionSpec = {  (slideInVertically { fullHeight -> fullHeight/2 } +
                           fadeIn(animationSpec = tween(200)))
                   .togetherWith(
                       slideOutVertically { fullHeight -> fullHeight/2 }  +
                               fadeOut(animationSpec = tween(200)))}
                )
                {
                    Text(
                        text = it.num1 + (it.operation?.symbol ?: "") + it.num2,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Light,
                        fontSize = 50.sp,
                        color = Color.White,
                        maxLines = 2,
                        lineHeight = 44.sp,
                        softWrap = true
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "AC",
                        modifier = Modifier
                            .background(LightGray)
                            .aspectRatio(2f)
                            .weight(2f),
                        onClick = {
                            onAction(CalculatorAction.Clear)
                        }
                    )

                    CalculatorButton(
                        symbol = "Del",
                        modifier = Modifier
                            .background(LightGray)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Delete)
                        }
                    )


                    CalculatorButton(
                        symbol = "÷",
                        modifier = Modifier
                            .background(Orange)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorAction.CalculatorOperation.Divide))
                        }
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "7",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number(7))
                        }
                    )

                    CalculatorButton(
                        symbol = "8",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Number(8))
                        }
                    )


                    CalculatorButton(
                        symbol = "9",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number(9))
                        }
                    )

                    CalculatorButton(
                        symbol = "x",
                        modifier = Modifier
                            .background(Orange)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorAction.CalculatorOperation.Multiply))
                        }
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "4",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number(4))
                        }
                    )

                    CalculatorButton(
                        symbol = "5",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Number(5))
                        }
                    )


                    CalculatorButton(
                        symbol = "6",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number(6))
                        }
                    )

                    CalculatorButton(
                        symbol = "-",
                        modifier = Modifier
                            .background(Orange)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorAction.CalculatorOperation.Subtract))
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "1",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number(1))
                        }
                    )

                    CalculatorButton(
                        symbol = "2",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Number(2))
                        }
                    )


                    CalculatorButton(
                        symbol = "3",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Number(3))
                        }
                    )

                    CalculatorButton(
                        symbol = "+",
                        modifier = Modifier
                            .background(Orange)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Operation(CalculatorAction.CalculatorOperation.Add))
                        }
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    CalculatorButton(
                        symbol = "0",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(2f)
                            .weight(2f),
                        onClick = {
                            onAction(CalculatorAction.Number(0))
                        }
                    )

                    CalculatorButton(
                        symbol = ".",
                        modifier = Modifier
                            .background(DarkGray)
                            .aspectRatio(1f)
                            .weight(1f),

                        onClick = {
                            onAction(CalculatorAction.Decimal)
                        }
                    )


                    CalculatorButton(
                        symbol = "=",
                        modifier = Modifier
                            .background(Orange)
                            .aspectRatio(1f)
                            .weight(1f),
                        onClick = {
                            onAction(CalculatorAction.Calculate)
                        }
                    )

                }


            }
        }
    }
}