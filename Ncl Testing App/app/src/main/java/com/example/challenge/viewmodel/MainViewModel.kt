package com.example.challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge.data.repo.MainRepository
import com.example.challenge.model.Ship
import com.example.challenge.utils.NetworkHelper
import com.example.challenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _users = MutableLiveData<Resource<Ship>>()
    val users: LiveData<Resource<Ship>>
        get() = _users


    fun fetchDetails(name:String) {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {
                    mainRepository.getShipDetails(name).let {
                        if (it.isSuccessful) {
                            _users.postValue(Resource.success(it.body()))
                        } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                } catch (ex: Exception) {
                    _users.postValue(Resource.error(ex.message ?: "Server Error", null))
                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }
}