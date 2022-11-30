package com.example.firebase.Entity

data class Order(
    var orderId:String,
    var UserId: String,
    var FlowerId: String,
    var FlowerName: String,
    var Quantity:Int,
    var Cost:Double,
    var OrderDate: String,
    var Status: String,
    var imageId:Int
)
