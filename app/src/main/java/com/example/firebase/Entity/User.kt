package com.example.firebase.Entity

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var userId: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var address: String? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
    var postalCode: String? = null,
    )
{}