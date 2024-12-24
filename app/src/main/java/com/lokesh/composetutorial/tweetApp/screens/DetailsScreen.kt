package com.lokesh.composetutorial.tweetApp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lokesh.composetutorial.tweetApp.viewModels.DetailsVM

@Composable
fun DetailsScreen(){
    val detailsVM : DetailsVM = hiltViewModel()
    val tweets = detailsVM.tweets.collectAsState()

    LazyColumn(
        content = {
            items(tweets.value.size){
                TweetListItem(tweet = tweets.value[it].text)
            }
        }
    )
}

@Composable
fun TweetListItem(tweet: String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 8.dp)
        .border(BorderStroke(2.dp, Color(0xFFEEEEEE)))) {

        Text(text = tweet,
            modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyMedium)
    }
}