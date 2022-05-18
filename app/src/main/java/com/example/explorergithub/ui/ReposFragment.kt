package com.example.explorergithub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.explorergithub.databinding.FragmentReposBinding
import com.example.explorergithub.ui.tracker.RepoAdapter
import android.net.Uri
import com.bumptech.glide.Glide
import com.example.explorergithub.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposFragment : Fragment() {

    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameTextView.text = viewModel.toUser.login

        Glide.with(this)
            .load(Uri.parse(viewModel.toUser.avatar_url))
            .placeholder(R.drawable.loading_spinner)
            .into(binding.imageView)

        val adapter= RepoAdapter()
        binding.repoRecyclerView.adapter = adapter
        val manager = LinearLayoutManager(context)
        binding.repoRecyclerView.layoutManager = manager
        viewModel.repoUser.observe(viewLifecycleOwner) {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}