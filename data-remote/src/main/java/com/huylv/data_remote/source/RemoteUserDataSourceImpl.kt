package com.huylv.data_remote.source

import com.huylv.data_remote.networking.user.UserApiModel
import com.huylv.data_remote.networking.user.UserService
import com.huylv.data_repository.data_source.remote.RemoteUserDataSource
import com.huylv.domain.entity.UseCaseException
import com.huylv.domain.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(private val userService: UserService) :
    RemoteUserDataSource {
    override fun getUsers(): Flow<List<User>> {
        return flow {
            emit(userService.getUsers())
        }.map { users ->
            users.map { model ->
                convert(model)
            }
        }.catch { throw UseCaseException.UserException(it) }
    }

    override fun getUser(id: Long): Flow<User> {
        return flow {
            emit(userService.getUser(id))
        }.map {
            convert(it)
        }.catch { throw UseCaseException.UserException(it) }
    }

    private fun convert(userApiModel: UserApiModel): User {
        return User(userApiModel.id, userApiModel.name, userApiModel.username, userApiModel.email)
    }
}