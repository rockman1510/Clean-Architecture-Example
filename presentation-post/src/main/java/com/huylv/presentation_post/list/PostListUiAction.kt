package com.huylv.presentation_post.list

import com.huylv.domain.entity.Interaction
import com.huylv.presentation_common.state.UiAction

sealed class PostListUiAction : UiAction {
    object Load : PostListUiAction()
    data class UserClick(val userId: Long, val interaction: Interaction) : PostListUiAction()
    data class PostClick(val postId: Long, val interaction: Interaction) : PostListUiAction()
}