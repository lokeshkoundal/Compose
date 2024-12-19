package com.lokesh.composetutorial.tweetApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lokesh.composetutorial.tweetApp.screens.App
import com.lokesh.composetutorial.ui.theme.ComposeTutorialTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var tweetApi: TweetApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeTutorialTheme {
//                CategoryScreen()
                App()
            }
        }
    }
}


@Composable
fun Tutorial(){
    ComposeTutorialTheme {
        var name by remember {
            mutableStateOf("")
        }
        var names by remember {
            mutableStateOf(listOf<String>())
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)) {

            Row(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(value = name , onValueChange = {text->
                    name = text
                }, placeholder = { Text(text = "Enter your name")},
                    label = { Text(text = "Username")},
                    modifier = Modifier.weight(1f))

                Spacer(modifier = Modifier.width(4.dp))

                Button(onClick = {
                    if(name.isNotBlank()){
                        names = names + name
                        name = ""
                    }
                }) {
                    Text(text = "Add")
                }
            }

            LazyColumn{
                items(names){currentName->
                    Text(text = currentName, modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp))
                    HorizontalDivider()
                }
            }
        }
    }
}