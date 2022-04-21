package com.example.explorergithub.model

import com.example.explorergithub.model.data.Repo
import com.example.explorergithub.model.data.User

interface IRepository {
    fun getUsers(): List<User>
    fun getPepo(user: User): List<Repo>
}