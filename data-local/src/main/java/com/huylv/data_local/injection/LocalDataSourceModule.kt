package com.huylv.data_local.injection

import com.huylv.data_local.source.LocalInteractionDataSourceImpl
import com.huylv.data_local.source.LocalPostDataSourceImpl
import com.huylv.data_local.source.LocalUserDataSourceImpl
import com.huylv.data_repository.data_source.local.LocalInteractionDataSource
import com.huylv.data_repository.data_source.local.LocalPostDataSource
import com.huylv.data_repository.data_source.local.LocalUserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindPostDataSource(postDataSourceImpl: LocalPostDataSourceImpl): LocalPostDataSource

    @Binds
    abstract fun bindUserDataSource(userDataSourceImpl: LocalUserDataSourceImpl): LocalUserDataSource

    @Binds
    abstract fun bindInteractionDataSource(interactionDataSourceImpl: LocalInteractionDataSourceImpl): LocalInteractionDataSource
}