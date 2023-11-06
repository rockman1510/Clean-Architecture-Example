package com.huylv.domain.usecase

import com.huylv.domain.entity.Interaction
import com.huylv.domain.entity.PostWithUser
import com.huylv.domain.repository.InteractionRepository
import com.huylv.domain.repository.PostRepository
import com.huylv.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetPostsWithUsersWithInteractionUseCase(
    configuration: UseCase.Configuration,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val interactionRepository: InteractionRepository
) : UseCase<GetPostsWithUsersWithInteractionUseCase.Request, GetPostsWithUsersWithInteractionUseCase.Response>(
    configuration
) {

    override fun process(request: Request): Flow<Response> = combine(
        postRepository.getPosts(),
        userRepository.getUsers(),
        interactionRepository.getInteraction()
    ) { posts, users, interaction ->
        val postUsers = posts.map { post ->
            val user = users.first() {
                it.id == post.userId
            }
            PostWithUser(post, user)
        }
        Response(postUsers, interaction)
    }

    object Request : UseCase.Request

    data class Response(
        val posts: List<PostWithUser>,
        val interaction: Interaction
    ) : UseCase.Response
}