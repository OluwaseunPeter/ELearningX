package com.app.miva.player.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.miva.chapters.domain.usecase.GetChaptersUseCase
import com.app.miva.chapters.presentation.state.ChaptersUiState
import com.app.miva.lessons.domain.model.Note
import com.app.miva.player.domain.usecase.GetLessonNotesUseCase
import com.app.miva.player.domain.usecase.SaveLessonNoteUseCase
import com.app.miva.player.presentation.screen.VideoPlayerControl
import com.app.miva.player.presentation.state.NoteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlayerViewmodel(
    private val getLessonsNoteUseCase: GetLessonNotesUseCase,
    private val saveLessonsNoteUseCase: SaveLessonNoteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    fun fetchVideoNotes(videoUrl: String) {
        viewModelScope.launch {
            getLessonsNoteUseCase.execute(videoUrl).collectLatest {
                _uiState.value = uiState.value.copy(notes = it)
            }
        }
    }

    fun saveVideoNote(note: Note) {
        viewModelScope.launch {
            saveLessonsNoteUseCase.execute(note)
        }
    }

    fun setCurrentNote(note: Note) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(currentNote = note)
        }
    }

    fun resetNoteState() {
        viewModelScope.launch {
            uiState.value.videoPlayerControl?.play()
            _uiState.value = uiState.value.copy(currentNote = null)
        }
    }

    fun setVideoPlayerControl(control: VideoPlayerControl) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(videoPlayerControl = control)
        }
    }
}