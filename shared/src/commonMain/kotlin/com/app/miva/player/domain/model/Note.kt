package com.app.miva.lessons.domain.model

data class Note(
    val noteId:Int? = null,
    val lessonId: String,
    val text: String,
    val title: String = "",
    val bookmark: Int = 0
)
