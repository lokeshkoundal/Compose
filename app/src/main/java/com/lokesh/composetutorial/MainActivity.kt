package com.lokesh.composetutorial

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lokesh.composetutorial.network.TweetApi
import com.lokesh.composetutorial.ui.theme.ComposeTutorialTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetApi: TweetApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            val res = tweetApi.getAllData()
            Log.d("Loki",res.body().toString())
        }
        setContent {
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
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ComposeTutorialTheme {
//        Greeting("Android")
//    }
//}