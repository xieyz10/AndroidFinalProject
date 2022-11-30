package com.example.firebase.Entity

data class Order(
    var UserId: String,
    var FlowerId: String,
    var Quantity:Int,
    var Cost:Double,
    var OrderDate: String,
    var Status: String
)
