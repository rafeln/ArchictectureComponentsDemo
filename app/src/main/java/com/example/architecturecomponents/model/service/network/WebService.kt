package com.example.architecturecomponents.model.service.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WebService {

    private var retrofit: Retrofit? = null
    private var service: WebServiceInterface? = null

    companion object {
        val API_URL = "https://api.github.com"
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MyInterceptor())
            .build()
    }

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()

        }
        return retrofit
    }

    @Provides
    fun getWebService(): WebService {
        getService()
        return this
    }

    @Provides
    fun getService(): WebServiceInterface? {
        if (service == null) {
            service = getRetrofit()?.create(WebServiceInterface::class.java)
        }
        return service
    }
}