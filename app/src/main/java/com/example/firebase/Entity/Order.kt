package com.example.firebase.Entity

data class Order(
    var CustomerId: Int,
    var ProductId: Int,
    var Quantity:Int,
    var Cost:Double,
    var OrderDate: String,
    var Status: String
)
