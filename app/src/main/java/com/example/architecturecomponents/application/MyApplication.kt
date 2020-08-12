package com.example.architecturecomponents.application

import android.app.Application
import android.content.Context
import com.example.architecturecomponents.model.dagger.ApplicationComponent
import com.example.architecturecomponents.model.dagger.DaggerApplicationComponent

class MyApplication: Application() {

    companion object {
        var appContext: Context? = null
    }

    // Create an instance of the application graph
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
    }
}