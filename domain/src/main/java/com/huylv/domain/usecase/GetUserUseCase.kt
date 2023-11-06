package com.huylv.domain.usecase

import com.huylv.domain.entity.User
import com.huylv.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUserUseCase(
    configuration: UseCase.Configuration,
    private val userRepository: UserRepository
) : UseCase<GetUserUseCase.Request, GetUserUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> {
        return userRepository.getUser(request.userId).map {
            Response(it)
        }
    }

    data class Request(val userId: Long) : UseCase.Request
    data class Response(val user: User) : UseCase.Response
}