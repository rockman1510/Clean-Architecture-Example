package com.huylv.data_remote.source

import com.huylv.data_remote.networking.post.PostApiModel
import com.huylv.data_remote.networking.post.PostService
import com.huylv.data_repository.data_source.remote.RemotePostDataSource
import com.huylv.domain.entity.Post
import com.huylv.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemotePostDataSourceImpl @Inject constructor(private val postService: PostService) :
    RemotePostDataSource {
    override fun getPosts(): Flow<List<Post>> = flow {
        emit(postService.getPosts())
    }.map { posts ->
        posts.map { postApiModel ->
            convert(postApiModel)
        }
    }.catch {
        throw UseCaseException.PostException(it)
    }

    private fun convert(postApiModel: PostApiModel): Post {
        return Post(postApiModel.id, postApiModel.userId, postApiModel.title, postApiModel.body)
    }

    override fun getPost(id: Long): Flow<Post> {
        return flow {
            emit(postService.getPost(id))
        }.map {
            convert(it)
        }.catch {
            throw UseCaseException.PostException(it)
        }
    }
}