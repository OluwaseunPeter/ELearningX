package com.app.miva.chapters.presentation.state

import com.app.miva.chapters.domain.model.Chapter

data class ChaptersUiState(
    val loading: Boolean = false,
    val chapters: List<Chapter> = emptyList()
)