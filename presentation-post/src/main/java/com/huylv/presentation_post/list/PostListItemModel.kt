package com.huylv.presentation_post.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostListItemModel(
    val id: Long,
    val userId: Long,
    val authorName: String,
    val title: String
) : Parcelable
