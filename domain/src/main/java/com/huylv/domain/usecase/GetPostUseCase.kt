package com.huylv.domain.usecase

import com.huylv.domain.entity.Post
import com.huylv.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPostUseCase(
    configuration: Configuration,
    private val postRepository: PostRepository
) : UseCase<GetPostUseCase.Request, GetPostUseCase.Response>(configuration) {

    public override fun process(request: Request): Flow<Response> {
        return postRepository.getPost(request.postId).map { Response(it) }
    }

    data class Request(val postId: Long) : UseCase.Request
    data class Response(val post: Post) : UseCase.Response
}