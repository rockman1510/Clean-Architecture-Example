package com.huylv.domain.repository

import com.huylv.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>
    fun getUser(id: Long): Flow<User>
}