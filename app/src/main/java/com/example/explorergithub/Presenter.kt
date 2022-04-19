package com.example.explorergithub

import com.example.explorergithub.data.User

class Presenter {
    private val listUser = listOf<User>(
        User("User1", 1, "http:/User1/"),
        User("User2", 2, "http:/User2/"),
        User("User3", 3, "http:/User3/")
    )

    fun getUsers(): List<User> {
        return listUser
    }
}