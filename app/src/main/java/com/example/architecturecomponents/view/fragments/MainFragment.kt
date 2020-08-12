package com.example.architecturecomponents.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.architecturecomponents.R
import com.example.architecturecomponents.databinding.FragmentMainBinding
import com.example.architecturecomponents.view.activities.MainActivity
import com.example.architecturecomponents.view.adapters.MainAdapter


class MainFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        val adapter = MainAdapter()
        binding.recyclerViewMain.adapter = adapter
        binding.hasData = true
        val mainActivity = activity as MainActivity
        mainActivity.setSupportActionBar(binding.toolbar)
        getUsersIfNecessary(mainActivity)
        subscribeUi(mainActivity, adapter, binding)
        return binding.root
    }

    private fun getUsersIfNecessary(mainActivity: MainActivity) {
        if (mainActivity.viewModel.users == null) {
            mainActivity.showLoadingDialog()
            mainActivity.viewModel.getUsers()
        }
    }

    private fun subscribeUi(mainActivity: MainActivity, adapter: MainAdapter, binding: FragmentMainBinding) {
        mainActivity.viewModel.users?.observe(viewLifecycleOwner) { result ->
            mainActivity.dismissAlertDialog()
            binding.hasData = !result.isNullOrEmpty()
            adapter.submitList(result)
        }
        mainActivity.viewModel.messageError?.observe(viewLifecycleOwner) { result ->
            mainActivity.showAlertDialog(result, mainActivity.resources.getString(R.string.error))
        }
    }
}