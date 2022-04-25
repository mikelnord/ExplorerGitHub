package com.example.explorergithub.ui.tracker


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.explorergithub.databinding.ItemReposBinding
import com.example.explorergithub.model.Repo


class RepoAdapter :
    ListAdapter<Repo, RecyclerView.ViewHolder>(RepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemReposBinding.inflate(inflater, parent, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    fun addHeaderAndSubmitList(list: List<Repo>) {
        submitList(list)
    }

    inner class ViewHolder(private val binding: ItemReposBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repo) {
            binding.repoTextView.text = item.name
        }

    }

}


class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}


