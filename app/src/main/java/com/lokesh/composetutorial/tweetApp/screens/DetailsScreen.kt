package com.lokesh.composetutorial.tweetApp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lokesh.composetutorial.tweetApp.viewModels.DetailsVM

@Composable
fun DetailsScreen(){
    val detailsVM : DetailsVM = hiltViewModel()
    val tweets = detailsVM.tweets.collectAsState()

    Column {
        Text(text = "Tweets",
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp)),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            style = MaterialTheme.typography.bodyMedium
        )

        LazyColumn{
                items(tweets.value.size){
                    TweetListItem(tweet = tweets.value[it].text)
                }
            }

    }

}

@Composable
fun TweetListItem(tweet: String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 8.dp)
        .clip(RoundedCornerShape(12.dp))) {

        Text(text = tweet,
            modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyMedium)
    }
}