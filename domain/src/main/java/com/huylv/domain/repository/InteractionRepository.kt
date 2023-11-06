package com.huylv.domain.repository

import com.huylv.domain.entity.Interaction
import kotlinx.coroutines.flow.Flow

interface InteractionRepository {
    fun getInteraction(): Flow<Interaction>

    fun saveInteraction(interaction: Interaction): Flow<Interaction>
}