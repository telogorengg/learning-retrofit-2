package com.example.retrofitlearning.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val userId: String,

    @SerializedName("avatar")
    val userAvatar: String,

    @SerializedName("name")
    val userName: String,

    @SerializedName("email")
    val userEmail: String
)