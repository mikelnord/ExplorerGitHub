package com.example.explorergithub.data.api

import com.example.explorergithub.model.IRepository
import com.example.explorergithub.model.entity.Repo
import com.example.explorergithub.model.entity.User
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor()  : IRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api: IDataSource = retrofit.create(IDataSource::class.java)

    override fun getUsers(): Single<List<User>> {
        return api.listUsers()
    }

    override fun getRepo(user: User): Single<List<Repo>> {
        return api.listRepos(user.login)
    }
}