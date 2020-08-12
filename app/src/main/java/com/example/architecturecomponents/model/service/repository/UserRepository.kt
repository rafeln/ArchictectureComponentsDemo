package com.example.architecturecomponents.model.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.architecturecomponents.model.bo.User
import com.example.architecturecomponents.model.service.network.ApiException
import com.example.architecturecomponents.model.service.network.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(private val webservice: WebService) {

    @Throws(IOException::class)
    fun getUsers() : LiveData<List<User>> {
        val data: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
        webservice.getService()?.getUsers()?.let {
            it.enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    } else {
                        // TODO Error handling
//                        throw ApiException()
                    }
                }

                override fun onFailure(call: Call<List<User>>, throwable: Throwable) {
                    // TODO Error handling
//                    throw throwable
                }
            })
        }
        return data
    }
}