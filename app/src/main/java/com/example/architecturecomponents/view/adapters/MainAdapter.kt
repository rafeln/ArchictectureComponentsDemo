package com.example.architecturecomponents.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.architecturecomponents.R
import com.example.architecturecomponents.databinding.ListHeaderMainBinding
import com.example.architecturecomponents.databinding.ListItemMainBinding
import com.example.architecturecomponents.model.bo.User
import com.example.architecturecomponents.view.fragments.MainFragmentDirections


class MainAdapter: ListAdapter<User, RecyclerView.ViewHolder>(
    UserDiffCallback()
) {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_HEADER) {
            HeaderViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_header_main, parent, false
                )
            )
        } else {
            ItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_main, parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(getItem(position))
    }

    class HeaderViewHolder(private val binding: ListHeaderMainBinding): RecyclerView.ViewHolder(binding.root) {
    }

    class ItemViewHolder(private val binding: ListItemMainBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.user?.let { user ->
                    navigateToDetails(user, view)
                }
            }
        }

        private fun navigateToDetails(user: User, view: View) {
            val direction = MainFragmentDirections
                .actionMainFragmentToMainDetailFragment(user)
            view.findNavController().navigate(direction)
        }

        fun bind(user: User) {
            with(binding) {
                Glide
                    .with(binding.userImageView.context)
                    .load(user.avatar_url)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.ic_person)
                    .into(binding.userImageView)

                binding.user = user
                executePendingBindings()
            }
        }
    }
}

private class UserDiffCallback: DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem == newItem
    }
}