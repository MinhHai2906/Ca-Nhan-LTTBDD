package com.example.data_flow

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Page3(){
    Class4PageUi(pagescreen = page3())

}
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun Page3Preview(){
    Page3()
}