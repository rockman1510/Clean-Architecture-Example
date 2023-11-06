package com.huylv.presentation_common.state

import com.huylv.domain.entity.Result

abstract class CommonResultConverter<RESPONSE : Any, RESULT : Any> {
    fun convert(result: Result<RESPONSE>): UiState<RESULT> {
        return when (result) {
            is Result.Success -> {
                UiState.Success(convertSuccess(result.data))
            }
            is Result.Error -> {
                UiState.Error(result.exception.localizedMessage.orEmpty())
            }
        }
    }

    abstract fun convertSuccess(data: RESPONSE): RESULT
}