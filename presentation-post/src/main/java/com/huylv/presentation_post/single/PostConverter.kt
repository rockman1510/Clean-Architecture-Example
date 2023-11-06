package com.huylv.presentation_post.single

import android.content.Context
import com.huylv.domain.usecase.GetPostUseCase
import com.huylv.presentation_common.state.CommonResultConverter
import com.huylv.presentation_post.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetPostUseCase.Response, PostModel>() {

    override fun convertSuccess(data: GetPostUseCase.Response): PostModel {
        return PostModel(
            context.getString(R.string.title, data.post.title),
            context.getString(R.string.body, data.post.body)
        )
    }
}