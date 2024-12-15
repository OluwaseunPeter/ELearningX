package com.app.miva.player.presentation.state

import com.app.miva.lessons.domain.model.Note

data class NoteUiState(
    val notes: List<Note>  = emptyList(),
    val currentNote: Note? = null
)
