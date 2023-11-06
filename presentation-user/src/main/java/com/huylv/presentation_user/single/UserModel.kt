package com.huylv.presentation_user.single

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val name: String,
    val username: String,
    val email: String
) : Parcelable