package com.packagename.newsapp.models


import com.google.gson.annotations.SerializedName

data class NoteRequest(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)