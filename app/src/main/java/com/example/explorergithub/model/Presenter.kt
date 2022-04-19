package com.example.explorergithub.model

import com.example.explorergithub.model.data.User

class Presenter {
    private val listUser = listOf(
        User("User1", 1, "http:/User1/"),
        User("User2", 2, "http:/User2/"),
        User("User3", 3, "http:/User3/")
    )

    fun getUsers(): List<User> {
        return listUser
    }
}