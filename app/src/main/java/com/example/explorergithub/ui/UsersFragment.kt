package com.example.explorergithub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.explorergithub.R
import com.example.explorergithub.data.api.GitHubRepository
import com.example.explorergithub.databinding.FragmentUsersBinding
import com.example.explorergithub.model.entity.User
import com.example.explorergithub.ui.tracker.ClickListener
import com.example.explorergithub.ui.tracker.UsersAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel by  sharedViewModel<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UsersAdapter(
            ClickListener { user: User -> viewModel.onUserClicked(user) }
        )

        binding.usersRecyclerView.adapter = adapter
        val manager = LinearLayoutManager(context)
        binding.usersRecyclerView.layoutManager = manager
        viewModel.onShowUsers()
        viewModel.users.observe(viewLifecycleOwner) {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        }
        viewModel.navigateToUser.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    R.id.action_usersFragment_to_reposFragment
                )
                viewModel.doneNavigating()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}