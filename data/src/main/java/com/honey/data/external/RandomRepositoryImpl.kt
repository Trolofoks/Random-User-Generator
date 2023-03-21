package com.honey.data.external


class RandomRepositoryImpl (
    private val api: RandomUserApi
) : RandomRepository {

    override suspend fun getUser(): User {
        return api.getUser()
    }

    override suspend fun getUsers(amount: Int): List<User> {
        return api.getUsers(amount.toString())
    }

}
