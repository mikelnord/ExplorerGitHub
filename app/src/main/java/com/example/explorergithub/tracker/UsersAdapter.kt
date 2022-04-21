package com.example.explorergithub.tracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.explorergithub.model.data.User
import com.example.explorergithub.databinding.ItemUsersBinding


class UsersAdapter(private val clickListener: ClickListener) :
    ListAdapter<User, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemUsersBinding.inflate(inflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val userItem = getItem(position)
                holder.bind(userItem, clickListener)
            }
        }
    }

    fun addHeaderAndSubmitList(list: List<User>?) {
        submitList(list)
    }

    inner class ViewHolder(private val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User, clickListener: ClickListener) {
            binding.loginTextView.text = item.login
            binding.materialCardView.setOnClickListener {
                clickListener.onClick(item)
            }
        }

    }

}


class DiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

class ClickListener(val clickListener: (user: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}

