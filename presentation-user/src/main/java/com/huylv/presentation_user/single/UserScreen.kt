package com.huylv.presentation_user.single

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huylv.presentation_common.navigation.UserInput
import com.huylv.presentation_common.state.CommonScreen

@Composable
fun UserScreen(
    viewModel: UserViewModel,
    userInput: UserInput
) {
    viewModel.uiStateFlow.collectAsState().value.let { result ->
        CommonScreen(state = result) {
            val userModel by rememberSaveable { mutableStateOf(it) }
            ShowUser(userModel)
        }
    }
    LaunchedEffect(key1 = userInput.userId) {
        Log.d("HuyLV", "UserScreen: LaunchedEffect")
        viewModel.submitAction(UserUiAction.Load(userInput.userId))
    }
}

@Composable
fun ShowUser(userModel: UserModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = userModel.name)
        Text(text = userModel.username)
        Text(text = userModel.email)
    }
}