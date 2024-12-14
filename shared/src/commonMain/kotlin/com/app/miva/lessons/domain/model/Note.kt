package com.app.miva.lessons.domain.model

data class Note(
    val lessonId: String,
    val text: String,
    val title: String,
    val bookmark: Int
)
