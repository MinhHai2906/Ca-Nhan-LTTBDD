package com.example.data_flow

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Page4(){
    Class4PageUi(pagescreen = page4())

}
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun Page4Preview(){
    Page4()
}