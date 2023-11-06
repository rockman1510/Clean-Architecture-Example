package com.huylv.presentation_post.list

import androidx.lifecycle.viewModelScope
import com.huylv.domain.entity.Interaction
import com.huylv.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import com.huylv.domain.usecase.UpdateInteractionUseCase
import com.huylv.presentation_common.navigation.NavRoutes
import com.huylv.presentation_common.navigation.PostInput
import com.huylv.presentation_common.navigation.UserInput
import com.huylv.presentation_common.state.MviViewModel
import com.huylv.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsWithUsersWithInteractionUseCase,
    private val converter: PostListConverter,
    private val updateInteractionUseCase: UpdateInteractionUseCase
) : MviViewModel<PostListModel, UiState<PostListModel>, PostListUiAction, PostListUiSingleEvent>() {
    override fun initState(): UiState<PostListModel> {
        return UiState.Loading
    }

    override fun handleAction(action: PostListUiAction) {
        when (action) {
            is PostListUiAction.Load -> {
                loadPosts()
            }
            is PostListUiAction.PostClick -> {
                updateInteraction(action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenPostScreen(
                        NavRoutes.Post.routeForPost(PostInput(action.postId))
                    )
                )
            }
            is PostListUiAction.UserClick -> {
                updateInteraction(action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenUserScreen(
                        NavRoutes.User.routeForUser(UserInput(action.userId))
                    )
                )
            }
        }
    }

    fun solution(S: String): Int {
        val roadPatchMap: MutableMap<Int, Int> = HashMap()
        val road = S.toCharArray()
        for (i in road.indices) {
            val block = road[i]
            if (block != 'X') {
                continue
            }
            if (roadPatchMap.containsKey(i - 1) || roadPatchMap.containsKey(i - 2)) {
                continue
            }
            roadPatchMap[i] = 1
        }
        var sum = 0
        for (value in roadPatchMap.values) {
            sum += value
        }
        return sum
    }

    private fun loadPosts() {
        viewModelScope.launch {
            useCase.execute(GetPostsWithUsersWithInteractionUseCase.Request).map {
                converter.convert(it)
            }.collect {
                submitState(it)
            }
        }
    }

    private fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteractionUseCase.execute(
                UpdateInteractionUseCase.Request(
                    interaction.copy(totalClicks = interaction.totalClicks + 1)
                )
            ).collect()
        }
    }
}