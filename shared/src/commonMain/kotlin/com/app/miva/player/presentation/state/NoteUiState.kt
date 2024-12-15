package com.app.miva.player.presentation.state

import com.app.miva.lessons.domain.model.Note
import com.app.miva.player.presentation.screen.VideoPlayerControl

data class NoteUiState(
    val notes: List<Note>  = emptyList(),
    val currentNote: Note? = null,
    val videoPlayerControl: VideoPlayerControl? = null
)
