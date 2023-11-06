package com.huylv.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.huylv.domain.entity.Result
import com.huylv.domain.entity.UseCaseException
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<INPUT : UseCase.Request, OUTPUT : UseCase.Response>(private val configuration: Configuration) {

    fun execute(request: INPUT): Flow<Result<OUTPUT>> {
        return process(request).map {
            Result.Success(it) as Result<OUTPUT>
        }.flowOn(configuration.dispatcher)
            .catch {
                emit(Result.Error(UseCaseException.createFromThrowable(it)))
            }
    }

    internal abstract fun process(request: INPUT): Flow<OUTPUT>
    class Configuration(val dispatcher: CoroutineDispatcher)
    interface Request
    interface Response
}