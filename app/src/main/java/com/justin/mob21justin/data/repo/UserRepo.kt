package com.justin.mob21justin.data.repo

import com.justin.mob21justin.data.model.User

interface UserRepo {

     suspend fun addUser(id: String, user: User)

     suspend fun getUser(id: String): User?

     suspend fun updateUser(id: String, user: User)
}