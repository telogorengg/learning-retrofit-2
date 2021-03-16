package com.example.retrofitlearning.data.api

import com.example.retrofitlearning.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}