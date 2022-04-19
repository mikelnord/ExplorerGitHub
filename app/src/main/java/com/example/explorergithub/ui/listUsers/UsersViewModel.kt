package com.example.explorergithub.ui.listUsers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.explorergithub.model.Presenter
import com.example.explorergithub.model.data.User

class UsersViewModel(presenter: Presenter) : ViewModel() {
    lateinit var touser: User
    val users: MutableLiveData<List<User>> = MutableLiveData(presenter.getUsers())


    private val _navigateToUser = MutableLiveData<Boolean?>()
    val navigateToUser
        get() = _navigateToUser

    fun onUserClicked(user: User) {
        touser = user
        _navigateToUser.value = true
    }

    fun doneNavigating() {
        _navigateToUser.value = null
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