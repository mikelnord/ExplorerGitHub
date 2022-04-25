package com.example.explorergithub.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.explorergithub.model.IRepository
import com.example.explorergithub.model.Repo
import com.example.explorergithub.model.User
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UsersViewModel(private val repository: IRepository) : ViewModel() {
    lateinit var toUser: User
    val users: MutableLiveData<List<User>> = MutableLiveData()
    var repoUser:MutableLiveData<List<Repo>> = MutableLiveData()

    private val _navigateToUser = MutableLiveData<Boolean?>()
    val navigateToUser
        get() = _navigateToUser

    fun onUserClicked(user: User) {
        toUser = user
        onShowRepo(user)
        _navigateToUser.value = true
    }

    fun doneNavigating() {
        _navigateToUser.value = null
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onShowUsers() {
        compositeDisposable.add(
            repository
                .getUsers()
                .subscribeBy {
                    users.postValue(it)
                }
        )
    }
    private fun onShowRepo(user: User) {
        compositeDisposable.add(
            repository
                .getRepo(user)
                .subscribeBy {
                    repoUser.postValue(it)
                }
        )
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