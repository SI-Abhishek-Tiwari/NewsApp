package com.packagename.newsapp.models


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)