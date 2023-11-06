package com.huylv.data_local.source

import com.huylv.data_local.db.user.UserDao
import com.huylv.data_local.db.user.UserEntity
import com.huylv.data_repository.data_source.local.LocalUserDataSource
import com.huylv.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(private val userDao: UserDao) :
    LocalUserDataSource {
    override fun getUsers(): Flow<List<User>> {
        return userDao.getUsers()
            .map { users -> users.map { User(it.id, it.name, it.username, it.email) } }
    }

    override suspend fun addUsers(users: List<User>) {
        userDao.insertUsers(users.map {
            UserEntity(it.id, it.name, it.username, it.email)
        })
    }
}