package com.example.explorergithub.ui.listRepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.explorergithub.databinding.FragmentReposBinding
import com.example.explorergithub.ui.listUsers.UsersViewModel


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
        binding.nameTextView.text = viewModel.touser.login
        binding.urlTextView.text = viewModel.touser.avatar_url
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}