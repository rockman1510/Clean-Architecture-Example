@file:OptIn(ExperimentalFoundationApi::class)

package com.huylv.presentation_post.list

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.huylv.presentation_common.state.CommonScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostListScreen(
    viewModel: PostListViewModel,
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.submitAction(PostListUiAction.Load)
    }
    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
//            var postListModel by rememberSaveable { mutableStateOf(it) }
            val postListModel = it
            PostList(postListModel, { postListItem ->
                viewModel.submitAction(PostListUiAction.PostClick(postListItem.id, it.interaction))
            }, { postListItem ->
                viewModel.submitAction(
                    PostListUiAction.UserClick(postListItem.userId, it.interaction)
                )
            })
        }
    }

    LaunchedEffect(key1 = Unit) {
        Log.d("HuyLV", "PostListScreen: LaunchedEffect")
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is PostListUiSingleEvent.OpenPostScreen -> {
                    navController.navigate(it.navRoute)
                }

                is PostListUiSingleEvent.OpenUserScreen -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }

}

@Composable
fun PostList(
    postListModel: PostListModel,
    onItemClick: (PostListItemModel) -> Unit,
    onAuthorClick: (PostListItemModel) -> Unit,
) {
    val items = postListModel.items.toMutableStateList()
    val lazyState = rememberLazyListState()
    LazyColumn(state = lazyState) {
        item(postListModel.headerText) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = postListModel.headerText)
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.Black)
                    .padding(16.dp)
            ) {

            }
        }
        items(items) { item ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .combinedClickable(onClick = {
                        Log.d("HuyLV", "LazyList item onClick!")
                        onItemClick(item)
                    }, onLongClick = {
                        Log.d("HuyLV", "LazyList item onLongClick!")
                        items.remove(item)
                    })
            ) {
                ClickableText(text = AnnotatedString(text = item.authorName),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        letterSpacing = 1.sp
                    ),
                    onClick = {
                        onAuthorClick(item)
                    })
                Text(text = item.title)
            }
            Divider(
                modifier = Modifier.fillMaxWidth().height(10.dp),
                color = Color.LightGray
            )
        }
    }
}