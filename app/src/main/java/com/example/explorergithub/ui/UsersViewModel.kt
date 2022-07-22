package com.example.explorergithub.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.explorergithub.data.api.GitHubRepository
import com.example.explorergithub.model.entity.Repo
import com.example.explorergithub.model.entity.User
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val repository: GitHubRepository) : ViewModel() {
    lateinit var toUser: User
    val users: MutableLiveData<List<User>> = MutableLiveData()
    var repoUser: MutableLiveData<List<Repo>> = MutableLiveData()

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
