package com.huylv.data_repository.repository_impl

import com.huylv.data_repository.data_source.local.LocalUserDataSource
import com.huylv.data_repository.data_source.remote.RemoteUserDataSource
import com.huylv.domain.entity.User
import com.huylv.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class UserRepositoryImpl(
    private val remoteUserDataSource: RemoteUserDataSource,
    private val localUserDataSource: LocalUserDataSource
) : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        return remoteUserDataSource.getUsers().onEach { localUserDataSource.addUsers(it) }
    }

    override fun getUser(id: Long): Flow<User> {
        return remoteUserDataSource.getUser(id).onEach { localUserDataSource.addUsers(listOf(it)) }
    }
}