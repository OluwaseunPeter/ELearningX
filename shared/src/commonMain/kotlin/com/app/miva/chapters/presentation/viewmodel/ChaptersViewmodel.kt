package com.app.miva.chapters.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.miva.chapters.domain.usecase.GetChaptersUseCase
import com.app.miva.chapters.presentation.state.ChaptersUiState
import com.app.miva.lessons.domain.model.Lesson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChaptersViewmodel(private val getChaptersUseCase: GetChaptersUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(ChaptersUiState())
    val uiState: StateFlow<ChaptersUiState> = _uiState.asStateFlow()

    init {
        fetchChapters()
    }

    fun fetchChapters() {
        _uiState.value = uiState.value.copy(loading = true)
        viewModelScope.launch {
            val res = getChaptersUseCase.execute()
            _uiState.value = uiState.value.copy(loading = false, chapters = res)
        }
    }

    fun getLessonsForChapter(chapterId: String): List<Lesson> {
        return uiState.value.chapters.firstOrNull { it.id == chapterId }?.lessons.orEmpty()
    }
}