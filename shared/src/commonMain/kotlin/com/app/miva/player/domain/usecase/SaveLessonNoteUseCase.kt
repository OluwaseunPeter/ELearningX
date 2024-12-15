package com.app.miva.player.domain.usecase

import com.app.miva.lessons.domain.model.Note
import com.app.miva.player.domain.dao.LessonNoteDao

class SaveLessonNoteUseCase(private val dao: LessonNoteDao) {
    fun execute(note: Note) = dao.updateNote(note)
}