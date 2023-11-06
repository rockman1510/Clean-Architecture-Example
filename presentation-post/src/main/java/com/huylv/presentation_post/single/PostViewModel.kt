package com.huylv.presentation_post.single

import androidx.lifecycle.viewModelScope
import com.huylv.domain.usecase.GetPostUseCase
import com.huylv.presentation_common.state.MviViewModel
import com.huylv.presentation_common.state.UiSingleEvent
import com.huylv.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postUseCase: GetPostUseCase,
    private val postConverter: PostConverter
) : MviViewModel<PostModel, UiState<PostModel>, PostUiAction, UiSingleEvent>() {
    override fun initState(): UiState<PostModel> {
        return UiState.Loading
    }

    override fun handleAction(action: PostUiAction) {
        when (action) {
            is PostUiAction.Load -> {
                loadPost(action.postId)
            }
        }
    }

    private fun loadPost(postId: Long) {
        viewModelScope.launch {
            postUseCase.execute(GetPostUseCase.Request(postId)).map {
                postConverter.convert(it)
            }.collect {
                submitState(it)
            }
        }
    }

}