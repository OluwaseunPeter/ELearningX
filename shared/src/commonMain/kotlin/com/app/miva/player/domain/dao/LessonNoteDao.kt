package com.app.miva.player.domain.dao

import com.app.miva.lessons.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface LessonNoteDao {
    fun updateNote(note: Note)
    fun getAllLessonNote(lessonId: String): Flow<List<Note>>
}