package com.example.data_flow

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Page2(){
    Class4PageUi(pagescreen = page2())

}
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun Page2Preview(){
    Page2()
}