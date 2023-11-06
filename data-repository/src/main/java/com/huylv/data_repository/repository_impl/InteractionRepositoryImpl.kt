package com.huylv.data_repository.repository_impl

import com.huylv.data_repository.data_source.local.LocalInteractionDataSource
import com.huylv.domain.entity.Interaction
import com.huylv.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class InteractionRepositoryImpl(
    private val interactionDataSource: LocalInteractionDataSource
) : InteractionRepository {

    override fun getInteraction(): Flow<Interaction> {
        return interactionDataSource.getInteraction()
    }

    override fun saveInteraction(interaction: Interaction): Flow<Interaction> {
        return flow {
            interactionDataSource.saveInteraction(interaction)
            emit(Unit)
        }.flatMapLatest {
            getInteraction()
        }
    }
}