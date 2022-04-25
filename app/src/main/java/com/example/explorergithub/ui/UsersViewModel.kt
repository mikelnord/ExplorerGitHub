package com.example.explorergithub.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.explorergithub.model.IRepository
import com.example.explorergithub.model.Repo
import com.example.explorergithub.model.User

class UsersViewModel(private val repository: IRepository) : ViewModel() {
    lateinit var toUser: User
    val users: MutableLiveData<List<User>> = MutableLiveData(repository.getUsers())
    lateinit var repoUser:MutableLiveData<List<Repo>>

    private val _navigateToUser = MutableLiveData<Boolean?>()
    val navigateToUser
        get() = _navigateToUser

    fun onUserClicked(user: User) {
        toUser = user
        repoUser = MutableLiveData(repository.getRepo(toUser))
        _navigateToUser.value = true
    }

    fun doneNavigating() {
        _navigateToUser.value = null
    }


}

class UsersViewModelFactory(
    private val repository: IRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}