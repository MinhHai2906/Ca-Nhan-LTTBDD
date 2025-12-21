package com.example.oop_payment

import androidx.annotation.DrawableRes

sealed class PaymentMethod(
    val name: String,
    @get:DrawableRes val logo: Int
)

object PayPal : PaymentMethod(
    name = "PayPal",
    logo = R.drawable.paypal
)

object GooglePay : PaymentMethod(
    name = "GooglePay",
    logo = R.drawable.gpay
)

object ApplePay : PaymentMethod(
    name = "ApplePay",
    logo = R.drawable.apay
)
