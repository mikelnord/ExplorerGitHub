package com.example.explorergithub.data

import com.example.explorergithub.model.IRepository
import com.example.explorergithub.model.Repo
import com.example.explorergithub.model.User
import io.reactivex.rxjava3.core.Single

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

    override fun getUsers(): Single<List<User>> {
        return Single.just(listUser)
    }

    override fun getRepo(user: User): Single<List<Repo>> {
        return Single.just(listRepo.filter { it.owner == user })
    }

}