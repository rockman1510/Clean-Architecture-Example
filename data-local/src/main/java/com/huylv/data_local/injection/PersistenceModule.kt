package com.huylv.data_local.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.huylv.data_local.db.AppDatabase
import com.huylv.data_local.db.post.PostDao
import com.huylv.data_local.db.user.UserDao
import com.huylv.data_local.source.LocalInteractionDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my-preferences")

    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "my-database").build()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()

    @Provides
    fun providePostDao(appDatabase: AppDatabase): PostDao = appDatabase.postDao()

    @Provides
    fun provideLocalInteractionDataSourceImpl(@ApplicationContext context: Context) =
        LocalInteractionDataSourceImpl(context.dataStore)
}