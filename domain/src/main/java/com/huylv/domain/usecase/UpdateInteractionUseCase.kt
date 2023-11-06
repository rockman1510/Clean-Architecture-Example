package com.huylv.domain.usecase

import com.huylv.domain.entity.Interaction
import com.huylv.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UpdateInteractionUseCase(
    configuration: UseCase.Configuration,
    private val interactionRepository: InteractionRepository
) : UseCase<UpdateInteractionUseCase.Request, UpdateInteractionUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> {
        return interactionRepository.saveInteraction(request.interaction).map {
            Response
        }
    }

    data class Request(val interaction: Interaction) : UseCase.Request
    object Response : UseCase.Response
}