package com.example.explorergithub.model

interface IRepository {
    fun getUsers(): List<User>
    fun getRepo(user: User): List<Repo>
}