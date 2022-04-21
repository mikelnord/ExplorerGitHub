package com.example.explorergithub.model

import com.example.explorergithub.model.data.Repo
import com.example.explorergithub.model.data.User

class MockRepository : IRepository {

    private val user1 = User("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4")
    private val user2 = User("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4")
    private val user3 = User("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4")


    private val listUser = listOf(
        user1,
        user2,
        user3
    )

    private val listRepo = listOf(
        Repo(1, user1, "0daysoflaptops.github.io"),
        Repo(2, user1, "asteroids"),
        Repo(3, user2, "NasaLocator")
    )

    override fun getUsers(): List<User> {
        return listUser
    }

    override fun getPepo(user: User): List<Repo> {
        return listRepo.filter { it.owner == user }
    }

}