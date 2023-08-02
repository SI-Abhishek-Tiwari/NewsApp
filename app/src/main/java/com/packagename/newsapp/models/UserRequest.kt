package com.packagename.newsapp.models


import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)