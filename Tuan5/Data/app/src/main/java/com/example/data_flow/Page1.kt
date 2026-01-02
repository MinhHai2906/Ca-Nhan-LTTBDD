package com.example.data_flow

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Page1(){
    Class4PageUi(pagescreen = page1())

}
@Preview(showBackground = true,showSystemUi = true)
@Composable
fun Page1Preview(){
    Page1()
}