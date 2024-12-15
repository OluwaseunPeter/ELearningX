package com.app.miva.player.domain.usecase

import com.app.miva.player.domain.dao.LessonNoteDao

class GetLessonNotesUseCase(private val dao: LessonNoteDao) {
    fun execute(lessonId: String) = dao.getAllLessonNote(lessonId)
}