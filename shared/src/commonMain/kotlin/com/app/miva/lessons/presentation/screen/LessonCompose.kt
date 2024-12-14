package com.app.miva.chapters.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.miva.chapters.presentation.viewmodel.ChaptersViewmodel
import com.app.miva.core.presentation.AppScreens
import com.app.miva.player.presentation.screen.VIDEO_URL
import org.koin.compose.koinInject

@Composable
fun LessonCompose(chapterId: String, navHostController: NavHostController, viewModel: ChaptersViewmodel = koinInject()) {
    val chapters by viewModel.uiState.collectAsState()
    return LazyColumn {
        items(
            chapters.chapters.firstOrNull { it.id == chapterId }?.lessons.orEmpty()
        ) { item ->
            Box(modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 14.dp)
                .clickable {
                    navHostController.navigate("${AppScreens.Player.name}?$VIDEO_URL=${item.videoUrl}")
                }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.title)
                }
            }
        }
    }
}