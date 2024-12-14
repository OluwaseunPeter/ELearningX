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
import org.koin.compose.koinInject

const val CHAPTER_ID = "CHAPTER_ID"

@Composable
fun ChapterCompose(navController: NavHostController, viewModel: ChaptersViewmodel = koinInject()) {
    val chapters by viewModel.uiState.collectAsState()

    return LazyColumn {
        items(
            chapters.chapters
        ) { item ->
            Box(modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 14.dp)
                .clickable {
                    navController.navigate("${AppScreens.Lessons}?$CHAPTER_ID=${item.id}")
                }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(item.title)
                    Text("${item.lessons.size} lessons")
                }
            }
        }
    }
}