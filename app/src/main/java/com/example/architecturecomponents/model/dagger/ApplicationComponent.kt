package com.example.architecturecomponents.model.dagger

import com.example.architecturecomponents.model.service.network.WebService
import com.example.architecturecomponents.view.activities.MainActivity
import com.example.architecturecomponents.view.fragments.MainDetailFragment
import com.example.architecturecomponents.view.fragments.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [WebService::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: MainDetailFragment)
}