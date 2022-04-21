package com.example.explorergithub.model.data

data class User(
    val login:String,
    val id:Int,
    val avatar_url:String
)

data class Repo(
    val id:Int,
    val owner:User,
    val name:String
)
