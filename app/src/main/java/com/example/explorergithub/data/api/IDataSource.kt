package com.example.explorergithub.data.api

import com.example.explorergithub.model.Repo
import com.example.explorergithub.model.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("/users")
    fun listUsers(): Single<List<User>>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Single<List<Repo>>
}