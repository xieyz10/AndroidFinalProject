package com.example.firebase.Entity

data class Order(
    var orderId:String,
    var userId: String,
    var flowerId: String,
    var flowerName: String,
    var quantity:Int,
    var cost:Double,
    var orderDate: String,
    var status: String,
    var imageId:Int
)
