package com.example.tuan4


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.AlertDialog


@Composable
fun AlertDialogScreen(onBack: () -> Unit){
    val openDialog=remember{mutableStateOf(false)}
    var result by remember{mutableStateOf("")}
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
                text = "AlertDialog",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Click vào đây để chọn yes or no",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color=Color.Red,
            modifier=Modifier.padding(top=50.dp)
        )

        Button(onClick={openDialog.value=true},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            modifier=Modifier.padding(top=30.dp)
                .width(200.dp).height(70.dp),
            shape = RoundedCornerShape(80.dp)
            ){
            Text(text="Click Me",
                fontSize = 23.sp,)
        }
        if(result.isNotEmpty()){
            Text(
                text=result,
                fontSize=20.sp,
                fontWeight=FontWeight.Bold,
                color=Color.Red,
                modifier=Modifier.padding(top=30.dp),
            )
        }


        if (openDialog.value){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value=false },
                title = { Text(text = "Hello you") },
                text = { Text(text = "Bạn có muốn điểm cao không?") },
                confirmButton = {
                    Button(onClick = {
                        openDialog.value=false
                        result="Bạn cần học 1 ngày 5 tiếng để điểm cao"
                    }) {
                        Text(text = "Yes")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        openDialog.value=false
                        result="Lướt tiktok đi ní"
                    }) {
                        Text(text = "No")
                    }
                }

            )

        }


        }//Column
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AlertDialogScreenPreview() {
    Tuan4Theme(dynamicColor = false) {
        AlertDialogScreen(onBack = {})
    }
}
