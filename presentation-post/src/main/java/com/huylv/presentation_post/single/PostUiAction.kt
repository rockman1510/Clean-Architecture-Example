package com.huylv.presentation_post.single

import com.huylv.presentation_common.state.UiAction

sealed class PostUiAction : UiAction{
    data class Load(val postId: Long): PostUiAction()
}
