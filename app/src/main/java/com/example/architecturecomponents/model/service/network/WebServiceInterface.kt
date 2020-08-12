package com.example.architecturecomponents.model.service.network;

import com.example.architecturecomponents.model.bo.User
import retrofit2.Call
import retrofit2.http.GET

interface WebServiceInterface {

    @GET("/users")
    fun getUsers(): Call<List<User>>
}
