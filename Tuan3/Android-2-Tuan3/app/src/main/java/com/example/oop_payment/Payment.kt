package com.example.oop_payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PaymentScreen(modifier: Modifier = Modifier) {

    val paymentMethods = listOf(
        PayPal,
        GooglePay,
        ApplePay
    )

    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logopayment),
            contentDescription = "Header Logo",
            modifier = Modifier
                .size(120.dp)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Chọn hình thức thanh toán",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        paymentMethods.forEach { method ->
            PaymentOptionItem(
                paymentMethod = method,
                isSelected = (method == selectedMethod),
                onItemSelected = {
                    selectedMethod = if (selectedMethod == method) null
                    else method
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {  },
            enabled = selectedMethod != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp).padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Continue", fontSize = 22.sp)
        }
    }
}

@Composable
fun PaymentOptionItem(
    paymentMethod: PaymentMethod,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onItemSelected)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onItemSelected
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = paymentMethod.name,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )

        Image(
            painter = painterResource(id = paymentMethod.logo),
            contentDescription = null,
            modifier = Modifier.height(24.dp),
            contentScale = ContentScale.Fit
        )
    }
}
