package com.packagename.newsapp.models


import com.google.gson.annotations.SerializedName

data class NoteResponses(
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("__v")
    val v: Int
)