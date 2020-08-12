package com.example.architecturecomponents.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.architecturecomponents.R
import com.example.architecturecomponents.databinding.FragmentMainDetailBinding
import com.example.architecturecomponents.model.service.utilities.InjectorUtils
import com.example.architecturecomponents.view.activities.BaseActivity
import com.example.architecturecomponents.viewmodel.MainDetailViewModel

class MainDetailFragment: Fragment() {

    private val args: MainDetailFragmentArgs by navArgs()

    private val viewModel: MainDetailViewModel by viewModels {
        InjectorUtils.provideMainDetailViewModelFactory(args.user)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainDetailBinding.inflate(inflater, container, false)
        binding.user = viewModel.user

        Glide
            .with(binding.userImageView.context)
            .load(viewModel.user.avatar_url)
            .apply(RequestOptions().circleCrop())
            .placeholder(R.drawable.ic_person)
            .into(binding.userImageView)
        binding.repositoryLayout.setOnClickListener() {
            openBrowser()
        }
        binding.toolbar.title = " "
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    private fun openBrowser() {
        (activity as BaseActivity).openBrowser(viewModel.user.html_url)
    }
}