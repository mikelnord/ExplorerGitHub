package com.example.explorergithub.ui.listUsers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.explorergithub.model.Repository
import com.example.explorergithub.R
import com.example.explorergithub.model.data.User
import com.example.explorergithub.databinding.FragmentUsersBinding
import com.example.explorergithub.tracker.UsersAdapter
import com.example.explorergithub.tracker.ClickListener

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<UsersViewModel> {
        UsersViewModelFactory(
            Repository()
        )
    }

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