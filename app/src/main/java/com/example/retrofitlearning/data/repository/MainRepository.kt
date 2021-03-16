package com.example.retrofitlearning.data.repository

import com.example.retrofitlearning.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}