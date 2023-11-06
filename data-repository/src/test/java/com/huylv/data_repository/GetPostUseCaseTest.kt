package com.huylv.data_repository

import com.huylv.domain.entity.Post
import com.huylv.domain.repository.PostRepository
import com.huylv.domain.usecase.GetPostUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetPostUseCaseTest {
    private val postRepository = mock<PostRepository>()
    private val useCase = GetPostUseCase(mock(), postRepository)

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runBlockingTest {
        val request = GetPostUseCase.Request(0L)
        val post = Post(1L, 1L, "title", "body")
        whenever(postRepository.getPost(request.postId)).thenReturn(flowOf(post))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetPostUseCase.Response(post), response)
    }
}