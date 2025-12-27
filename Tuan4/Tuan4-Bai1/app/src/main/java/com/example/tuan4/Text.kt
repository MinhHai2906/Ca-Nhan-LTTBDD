package com.example.tuan4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan4.ui.theme.Tuan4Theme
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable
fun TextScreen(onBack: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 10.dp)
        ) {

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Quay lại",
                    tint = Color.Blue
                )
            }

            Text(
                text = "Text Screen",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
        

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter 
        ) {
            Text(

                modifier = Modifier.padding(top = 150.dp),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            textDecoration = TextDecoration.LineThrough
                        )
                    ) {
                        append("The quick")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFFFF9800)
                        )
                    ) {
                        append(" Brown ")
                    }

                    append("fox jumps ")

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("over ")
                    }
                    withStyle(style = SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                    ){
                        append("the")

                    }

                    withStyle(
                        style = SpanStyle(
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append(" lazy ")
                    }

                    append("dog.")
                },
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )

        }

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextScreenPreview() {
    Tuan4Theme(dynamicColor = false) {
        TextScreen(onBack = {})
    }
}
