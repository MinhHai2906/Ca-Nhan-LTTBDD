package com.example.oop_navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

open class Class3Page(
    @DrawableRes val image:Int,
    val text1:String,
    val text2:String,
    val button:String,
    @DrawableRes val iconBack:Int
)
class page1: Class3Page(
    image=R.drawable.anh1,
    text1 = "Easy Time Management",
    text2 = "With management based on priority and daily tasks," +
            " it will give you convenience in managing and determining the tasks that must be done first.",
    button = "Next",
    iconBack = R.drawable.ic_back_24
)
class page2: Class3Page(
    image=R.drawable.anh2,
    text1 = "Increase Work Effectiveness",
    text2 = "Time management and the determination of more important tasks will give your job statistics better and always improve.",
    button = "Next",
    iconBack = R.drawable.ic_back_24
)
class page3: Class3Page(
    image=R.drawable.anh3,
    text1 = "Reminder Notification",
    text2 = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set.",
    button = "Next",
    iconBack = R.drawable.ic_back_24
)

@Composable
fun Class3PageUi(
    pagescreen:Class3Page,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
){
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top){

        Image(
            painter = painterResource(pagescreen.image),
            contentDescription = "page1 Image",
            modifier=Modifier.padding(top=200.dp).size(200.dp)
        )
        Text(text = pagescreen.text1,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = pagescreen.text2,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier=Modifier.weight(1f))
        Box(
            modifier=Modifier.fillMaxWidth()
                .padding(bottom=60.dp),
        ){
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp)
            ) {
                Icon(
                    painter = painterResource(pagescreen.iconBack),
                    contentDescription = "Back Icon",
                    tint = Color.Blue,
                    modifier = Modifier.size(38.dp)
                )
            }
            Button(
                onClick = onNextClick,
                modifier=Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterEnd)
                    .padding(end=10.dp)
                    .height(60.dp),
                colors=ButtonDefaults.buttonColors(Color.Blue)
            ){
                Text(text=pagescreen.button,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}