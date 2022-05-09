package com.example.explorergithub.model

import com.example.explorergithub.model.entity.Repo
import com.example.explorergithub.model.entity.User
import io.reactivex.rxjava3.core.Single

interface IRepository {
    fun getUsers(): Single<List<User>>
    fun getRepo(user: User): Single<List<Repo>>
}