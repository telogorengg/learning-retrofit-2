package com.example.retrofitlearning.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.retrofitlearning.data.repository.MainRepository
import com.example.retrofitlearning.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))

        try
        {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch(exception: Exception)
        {
            emit(Resource.error(data = null, message = exception.message ?: "Error occured!"))
        }
    }
}