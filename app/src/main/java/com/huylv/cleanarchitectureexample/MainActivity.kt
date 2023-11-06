package com.huylv.cleanarchitectureexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huylv.cleanarchitectureexample.ui.theme.CleanArchitectureExampleTheme
import com.huylv.presentation_common.navigation.NavRoutes
import com.huylv.presentation_post.list.PostListScreen
import com.huylv.presentation_post.single.PostScreen
import com.huylv.presentation_user.single.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }

    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Posts.route) {
        composable(route = NavRoutes.Posts.route) {
            Log.d("HuyLV", "Composable: PostListScreen")
            PostListScreen(viewModel = hiltViewModel(), navController = navController)
        }
        composable(
            route = NavRoutes.Post.route,
            arguments = NavRoutes.Post.arguments
        ) {
            Log.d("HuyLV", "Composable: PostScreen")
            PostScreen(viewModel = hiltViewModel(), postInput = NavRoutes.Post.fromEntry(it))
        }
        composable(route = NavRoutes.User.route, arguments = NavRoutes.User.arguments) {
            Log.d("HuyLV", "Composable: UserScreen")
            UserScreen(viewModel = hiltViewModel(), userInput = NavRoutes.User.fromEntry(it))
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CleanArchitectureExampleTheme {
        Greeting("Android")
    }
}