package com.lokesh.composetutorial.tweetApp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lokesh.composetutorial.R
import com.lokesh.composetutorial.tweetApp.viewModels.CategoriesVM

@Composable
fun CategoryScreen(onClick :(category:String)->Unit){

    val categoriesVM : CategoriesVM = hiltViewModel()
    val categories: State<List<String>> = categoriesVM.categories.collectAsState()


    LazyVerticalGrid(columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        items(categories.value.distinct()){
            CategoryItem(category = it,onClick)
        }
    }
}

@Composable
fun CategoryItem(category : String,onClick: (category:String) -> Unit){
    Box(modifier = Modifier
        .size(160.dp, 160.dp)
        .padding(4.dp,8.dp)
        .paint(painterResource(id = R.drawable.ic_bg), contentScale = ContentScale.FillBounds)
//        .border(3.dp, Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
        .clip(RoundedCornerShape(8.dp))
        .padding(4.dp,8.dp)
        .clickable { onClick(category) },
        contentAlignment = Alignment.Center) {

//        Image(painter = rememberVectorPainter(image = Icons.Default.Star), contentDescription = "Logo")
        Text(text = category,
            color = Color.White,
            modifier = Modifier.padding(0.dp,20.dp),
            style = MaterialTheme.typography.headlineMedium)
    }
}