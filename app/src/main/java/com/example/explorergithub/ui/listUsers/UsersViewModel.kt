package com.example.explorergithub.ui.listUsers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.explorergithub.Presenter
import com.example.explorergithub.data.User

class UsersViewModel(presenter: Presenter) : ViewModel() {
    private var touser = MutableLiveData<User?>()
    val users : MutableLiveData<List<User>> = MutableLiveData(presenter.getUsers())

    fun onUserClicked(user:User) {

    }

}

class UsersViewModelFactory(
    private val presenter: Presenter
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(presenter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}