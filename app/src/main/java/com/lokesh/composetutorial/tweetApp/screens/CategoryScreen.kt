package com.lokesh.composetutorial.tweetApp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lokesh.composetutorial.tweetApp.viewModels.CategoriesVM

@Composable
fun CategoryScreen(onClick :(category:String)->Unit){

    val categoriesVM : CategoriesVM = hiltViewModel()
    val categories: State<List<String>> = categoriesVM.categories.collectAsState()
    val isLoading: State<Boolean> = categoriesVM.isLoading.collectAsState()


    if(isLoading.value){
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }

    }else{
        LazyColumn(Modifier.fillMaxSize(),
            contentPadding = PaddingValues(6.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            items(categories.value.distinct()){
                CategoryItem(category = it,onClick)
            }
        }
    }
}

@Composable
fun CategoryItem(category : String,onClick: (category:String) -> Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(72.dp)
        .padding(4.dp,8.dp)
        .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(12.dp))
        .clip(RoundedCornerShape(12.dp))
        .clickable { onClick(category) },
        contentAlignment = Alignment.Center) {
        Text(text = category,
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium)
    }
}