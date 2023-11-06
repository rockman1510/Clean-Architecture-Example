package com.huylv.cleanarchitectureexample.injection

import com.huylv.data_repository.data_source.local.LocalInteractionDataSource
import com.huylv.data_repository.data_source.local.LocalPostDataSource
import com.huylv.data_repository.data_source.local.LocalUserDataSource
import com.huylv.data_repository.data_source.remote.RemotePostDataSource
import com.huylv.data_repository.data_source.remote.RemoteUserDataSource
import com.huylv.data_repository.repository_impl.InteractionRepositoryImpl
import com.huylv.data_repository.repository_impl.PostRepositoryImpl
import com.huylv.data_repository.repository_impl.UserRepositoryImpl
import com.huylv.domain.repository.InteractionRepository
import com.huylv.domain.repository.PostRepository
import com.huylv.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun providePostRepository(
        remotePostDataSource: RemotePostDataSource,
        localPostDataSource: LocalPostDataSource
    ): PostRepository = PostRepositoryImpl(remotePostDataSource, localPostDataSource)

    @Provides
    fun provideUserRepository(
        remoteUserDataSource: RemoteUserDataSource,
        localUserDataSource: LocalUserDataSource
    ): UserRepository = UserRepositoryImpl(remoteUserDataSource, localUserDataSource)

    @Provides
    fun provideInteractionRepository(
        interactionDataSource: LocalInteractionDataSource
    ): InteractionRepository = InteractionRepositoryImpl(interactionDataSource)
}