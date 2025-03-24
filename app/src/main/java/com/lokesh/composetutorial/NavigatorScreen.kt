package com.lokesh.composetutorial

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewNavigatorScreen() {
    NavigatorScreen(navController = rememberNavController())
}

@Composable
fun NavigatorScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()){
        Text("Home",
            fontFamily = FontFamily(Font(R.font.nunito_bold)), fontSize = 24.sp, fontWeight = FontWeight.Bold,
            modifier =  Modifier.align(Alignment.TopCenter)
                .padding(vertical = 30.dp))

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 6.dp)
                .verticalScroll(rememberScrollState())) {


                NavButton(
                    onClick = { navController.navigate(Graphs.CalculatorGraph.route) },
                    name = "Calculator",
                    id = null,
                    imgId = R.drawable.bg_maths,
                    textColor = Color.Black

                )


                NavButton(
                    onClick = { navController.navigate(Graphs.OnlineQuiz.route) },
                    name = "Online Quiz",
                    id = null,
                    imgId = R.drawable.bg_quiz,
                    textColor = Color.Black
                )


                NavButton(
                    onClick = { navController.navigate(Graphs.ExtraGraph.route) },
                    name = "See More",
                    id = null,
                    imgId = R.drawable.bg_see_more,
                    textColor = Color.White

                )

            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavButton(name:String,id:Int?,imgId:Int?,textColor : Color?, onClick: () -> Unit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = if(imgId!=null)Color.Transparent else Color(0xFF667BEC))
                .combinedClickable(
                    onClick = { onClick() },
                    onDoubleClick = {}
                )
        ) {

            if(imgId!=null){
                Image(
                    painter = painterResource(id = imgId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            if(id!=null){
                Icon(
                    painter = painterResource(id),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterStart),
                )
            }

            Text(
                text = name,
                fontSize = 22.sp,
                color = textColor ?: Color.Unspecified,
                modifier = Modifier.align(Alignment.Center).padding(6.dp),
            )
        }

}