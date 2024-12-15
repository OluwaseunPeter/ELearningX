package com.app.miva.lessons.data.dao

import app.cash.sqldelight.coroutines.asFlow
import com.app.miva.db.LessonNoteDb
import com.app.miva.db.LessonNoteTableQueries
import com.app.miva.lessons.domain.model.Note
import com.app.miva.player.domain.dao.LessonNoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LessonNoteDaoImpl(
    val mapper: LessonNoteDbMapper = LessonNoteDbMapper(),
    private val query: LessonNoteTableQueries
) :
    LessonNoteDao {

    override fun updateNote(note: Note) {
        if (note.noteId == null) {
            query.addNewNote(mapper.fromDomain(note))
        } else {
            query.saveNote(mapper.fromDomain(note))
        }
    }

    override fun getAllLessonNote(lessonId: String): Flow<List<Note>> {
        return query.getAllNoteByLessonId(lessonId).asFlow().map {
            it.executeAsList().map(mapper::toDomain)
        }
    }

}


class LessonNoteDbMapper {
    fun toDomain(lessonNoteDb: LessonNoteDb): Note {
        return Note(
            noteId = lessonNoteDb.id.toInt(),
            lessonId = lessonNoteDb.lessonId,
            title = lessonNoteDb.title.orEmpty(),
            text = lessonNoteDb.body.orEmpty(),
            bookmark = lessonNoteDb.bookmark.toInt()
        )
    }

    fun fromDomain(note: Note): LessonNoteDb {
        return LessonNoteDb(
            id = note.noteId?.toLong() ?: -1L,
            lessonId = note.lessonId,
            title = note.title,
            body = note.text,
            bookmark = note.bookmark.toLong()
        )
    }
}