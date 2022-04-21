package com.example.explorergithub.ui.listUsers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.explorergithub.model.Repository
import com.example.explorergithub.model.data.Repo
import com.example.explorergithub.model.data.User

class UsersViewModel(private val repository: Repository) : ViewModel() {
    lateinit var toUser: User
    val users: MutableLiveData<List<User>> = MutableLiveData(repository.getUsers())
    lateinit var repoUser:MutableLiveData<List<Repo>>

    private val _navigateToUser = MutableLiveData<Boolean?>()
    val navigateToUser
        get() = _navigateToUser

    fun onUserClicked(user: User) {
        toUser = user
        repoUser = MutableLiveData(repository.getPepo(toUser))
        _navigateToUser.value = true
    }

    fun doneNavigating() {
        _navigateToUser.value = null
    }


}

class UsersViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}