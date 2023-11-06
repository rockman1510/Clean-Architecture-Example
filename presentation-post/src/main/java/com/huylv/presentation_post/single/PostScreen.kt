package com.huylv.presentation_post.single

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huylv.presentation_common.navigation.PostInput
import com.huylv.presentation_common.state.CommonScreen

@Composable
fun PostScreen(
    viewModel: PostViewModel,
    postInput: PostInput
) {
    viewModel.uiStateFlow.collectAsState().value.let { result ->
        CommonScreen(state = result) {
//            val postModel by rememberSaveable { mutableStateOf(it) }
            ShowPost(it)
        }
    }
    LaunchedEffect(key1 = postInput.postId) {
        Log.d("HuyLV", "PostScreen: LaunchedEffect")
        viewModel.submitAction(PostUiAction.Load(postInput.postId))
    }
}

@Composable
fun ShowPost(postModel: PostModel) {
    var title by rememberSaveable {
        mutableStateOf(postModel.title)
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = title)
        Text(text = postModel.body)
        OutlinedTextField(value = title, onValueChange = {title = it})
    }
}
