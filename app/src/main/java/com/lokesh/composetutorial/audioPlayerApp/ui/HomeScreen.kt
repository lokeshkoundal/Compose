package com.lokesh.composetutorial.audioPlayerApp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokesh.composetutorial.R

@Composable
fun HomeScreen(){
    Column(modifier = Modifier
        .background(Color(0xFF181557))
        .fillMaxSize()
        .padding(18.dp)
        ) {
        TopBar()
        Spacer(modifier = Modifier.height(10.dp))
        TitleNSearchRow()
    }
}


@Composable
fun TopBar(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 0.dp, 0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Row {
            Image(
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.user_dp),
                contentDescription = "dp",
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.padding(6.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Lokesh Android", fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_bold)), color = Color.LightGray
                )

                Text(
                    text = "Gold Member", fontSize = 18.sp, color = Color.LightGray,
                    fontFamily = FontFamily(Font(R.font.nunito_bold))
                )
            }

        }
            Button(onClick = { }, modifier = Modifier
                .size(36.dp)
                .background(Color.Transparent),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF181557)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_bell), contentDescription = "bell",
                    modifier = Modifier.fillMaxSize())
            }

    }
}

@Composable
fun TitleNSearchRow(){
//    var isActive by remember { mutableStateOf(false) }

    Row (horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){

        Text(text = "Listen the Latest Musics",
            fontFamily = FontFamily(Font(R.font.nunito_bold)),
            fontSize = 20.sp,
            color = Color.White)


//        SearchBar(
//            inputField = { TextField(value = , onValueChange = ) },
//            expanded = false,
//            onExpandedChange = {},
//            active = isActive,
//        ) {
//
//        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}
