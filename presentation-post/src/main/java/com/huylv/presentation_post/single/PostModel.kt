package com.huylv.presentation_post.single

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostModel(
    val title: String,
    val body: String
) : Parcelable