package com.example.a3_oop_book

sealed class BookClass (
    val book : String,
    val name:String
)
object Book1:BookClass("Sách1")
object Book2:BookClass("Sách2")
object Book3:BookClass("Sách3")
object Book4:BookClass("Sách4")
object Book5:BookClass("Sách5")