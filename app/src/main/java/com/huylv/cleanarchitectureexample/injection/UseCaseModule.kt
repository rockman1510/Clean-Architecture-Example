package com.huylv.cleanarchitectureexample.injection

import com.huylv.domain.repository.InteractionRepository
import com.huylv.domain.repository.PostRepository
import com.huylv.domain.repository.UserRepository
import com.huylv.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetPostWithUsersWithInteractionUseCase(
        configuration: UseCase.Configuration,
        postRepository: PostRepository,
        userRepository: UserRepository,
        interactionRepository: InteractionRepository
    ): GetPostsWithUsersWithInteractionUseCase = GetPostsWithUsersWithInteractionUseCase(
        configuration,
        postRepository,
        userRepository,
        interactionRepository
    )

    @Provides
    fun provideGetPostUseCase(
        configuration: UseCase.Configuration,
        postRepository: PostRepository
    ): GetPostUseCase = GetPostUseCase(
        configuration, postRepository
    )

    @Provides
    fun provideGetUserUseCase(
        configuration: UseCase.Configuration,
        userRepository: UserRepository
    ): GetUserUseCase = GetUserUseCase(configuration, userRepository)

    @Provides
    fun provideUpdateInteractionUseCase(
        configuration: UseCase.Configuration,
        interactionRepository: InteractionRepository
    ): UpdateInteractionUseCase = UpdateInteractionUseCase(
        configuration, interactionRepository
    )
}