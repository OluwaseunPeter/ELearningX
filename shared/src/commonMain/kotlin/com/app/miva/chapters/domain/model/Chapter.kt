package com.app.miva.chapters.domain.model

import com.app.miva.lessons.domain.model.Lesson

data class Chapter(
    val id: String,
    val title: String,
    val lessons: List<Lesson>
)
