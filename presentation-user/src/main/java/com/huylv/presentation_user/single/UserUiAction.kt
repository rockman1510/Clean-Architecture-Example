package com.huylv.presentation_user.single

import com.huylv.presentation_common.state.UiAction

sealed class UserUiAction : UiAction {
    data class Load(val userId: Long) : UserUiAction()
}
