package com.example.explorergithub.ui.listUsers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.explorergithub.Presenter
import com.example.explorergithub.data.User
import com.example.explorergithub.databinding.FragmentUsersBinding
import com.example.explorergithub.tracker.Adapter
import com.example.explorergithub.tracker.ClickListener

class UsersFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUsersBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(
            this,
            UsersViewModelFactory(Presenter())
        ).get(UsersViewModel::class.java)

        val adapter = Adapter(
            ClickListener { user: User -> onUserClicked(user) }
        )

        binding.usersRecyclerView.adapter = adapter
        val manager = LinearLayoutManager(context)
        binding.usersRecyclerView.layoutManager = manager

        viewModel.users.observe(viewLifecycleOwner) {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        }

        return binding.root
    }

    private fun onUserClicked(user: User) {
        Toast.makeText(context, user.login, Toast.LENGTH_SHORT).show()
    }

}