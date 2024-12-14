package com.app.miva.chapters.data.model

import com.app.miva.chapters.domain.model.Chapter
import com.app.miva.lessons.domain.model.Lesson
import kotlinx.serialization.Serializable


@Serializable
data class ChapterApiResponse(
    val title: String,
    val lessons: List<ChapterLesson> = emptyList()
)

fun ChapterApiResponse.toDomain(): Chapter {
    return Chapter(
        id = title.toId(),
        title = title,
        lessons = lessons.map(ChapterLesson::toDomain)
    )
}

@Serializable
data class ChapterLesson(
    val title: String,
    val videoUrl: String
)

fun ChapterLesson.toDomain(): Lesson {
    return Lesson(
        id = videoUrl.toId(),
        title = title,
        videoUrl = videoUrl
    )
}

fun String.toId(): String {
    return this.trim().lowercase().replace(" ", "")
}