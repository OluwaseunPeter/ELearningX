package com.app.miva.core.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.miva.chapters.presentation.screen.CHAPTER_ID
import com.app.miva.chapters.presentation.screen.ChapterCompose
import com.app.miva.lessons.presentation.screen.LessonCompose
import com.app.miva.chapters.presentation.viewmodel.ChaptersViewmodel
import com.app.miva.player.presentation.screen.VIDEO_URL
import com.app.miva.player.presentation.screen.VideoPlayerCompose
import org.koin.compose.viewmodel.koinViewModel


/**
 * enum values that represent the screens in the app
 */
enum class AppScreens(val title: String) {
    Dashboard(title = "Dashboard"),
    Chapters(title = "Chapters"),
    Lessons(title = "Lessons"),
    Player(title = "Player")
}


@Composable
fun RootCompose() {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = AppScreens.valueOf(
        backStackEntry?.destination?.route?.substringBefore("?") ?: AppScreens.Chapters.name
    )
    val chaptersViewmodel: ChaptersViewmodel  = koinViewModel()

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreens.Chapters.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = AppScreens.Chapters.name) {
                ChapterCompose(navController, chaptersViewmodel)
            }
            composable(route = "${AppScreens.Lessons.name}?$CHAPTER_ID={$CHAPTER_ID}") {
                val chapterId = it.arguments?.getString(CHAPTER_ID)
                LessonCompose(chapterId.orEmpty(), navController, chaptersViewmodel)
            }
            composable(route = "${AppScreens.Player.name}?$VIDEO_URL={$VIDEO_URL}") {
                val videoUrl = it.arguments?.getString(VIDEO_URL)
                VideoPlayerCompose(videoUrl.orEmpty())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: AppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        }
    )
}
