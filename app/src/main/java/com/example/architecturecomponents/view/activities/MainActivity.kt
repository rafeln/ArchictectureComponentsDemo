package com.example.architecturecomponents.view.activities

import android.os.Bundle
import com.example.architecturecomponents.R
import com.example.architecturecomponents.application.MyApplication
import com.example.architecturecomponents.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity: BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (applicationContext as MyApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
    }
}
