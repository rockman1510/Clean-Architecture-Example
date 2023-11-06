package com.huylv.presentation_post.list

import android.os.Parcelable
import com.huylv.domain.entity.Interaction
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostListModel(
    val headerText: String = "",
    val items: List<PostListItemModel> = listOf(),
    val interaction: Interaction
) : Parcelable