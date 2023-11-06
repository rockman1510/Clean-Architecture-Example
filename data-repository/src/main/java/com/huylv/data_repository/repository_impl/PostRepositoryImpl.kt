package com.huylv.data_repository.repository_impl

import com.huylv.data_repository.data_source.local.LocalPostDataSource
import com.huylv.data_repository.data_source.remote.RemotePostDataSource
import com.huylv.domain.entity.Post
import com.huylv.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class PostRepositoryImpl(
    private val remotePostDataSource: RemotePostDataSource,
    private val localPostDataSource: LocalPostDataSource
) : PostRepository {

    override fun getPosts(): Flow<List<Post>> {
        return remotePostDataSource.getPosts().onEach {
            localPostDataSource.addPosts(it)
        }
    }

    override fun getPost(id: Long): Flow<Post> {
        return remotePostDataSource.getPost(id).onEach {
            localPostDataSource.addPosts(listOf(it))
        }
    }
}