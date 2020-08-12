package com.example.architecturecomponents.model.service.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.example.architecturecomponents.BuildConfig
import com.example.architecturecomponents.application.MyApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import kotlin.text.Charsets.UTF_8

class MyInterceptor: Interceptor {

    val tag = "MyInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        // TODO Implement this method for API >= 29
//        if (!isOnline()) {
//            throw NoInternetException()
//        }
        val request = chain.request()
        logRequest(request)
        val response = chain.proceed(request)
        logResponse(request, response)
        return response
    }

    fun isOnline(): Boolean {
        val cm = MyApplication.appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        val activeNetwork: NetworkInfo? = cm?.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun logRequest(request: Request) {
        if (BuildConfig.DEBUG) {
            Log.d(tag,"Requesting " + request.method() + ": " + request.url())
            if (request.headers().size() > 0) {
                Log.d(tag,"Headers: " + request.headers())
            }
            request.body()?.let {
                val buffer = Buffer()
                it.writeTo(buffer)
                Log.d(tag,"Body: " + buffer.readString(UTF_8))
            }
        }
    }

    private fun logResponse(request: Request, response: Response) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "Response status " + response.code() + " for " + request.method() + ": " + request.url())
            response.body()?.source()?.let { source ->
                source.request(Long.MAX_VALUE) // Buffer the entire body.
                val buffer = source.buffer
                Log.d(tag, "Response body: " + buffer.clone().readString(UTF_8))
            }
        }
    }
}