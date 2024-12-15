package com.app.miva.chapters.domain.repository

import com.app.miva.chapters.domain.model.Chapter

interface ChapterRepository {
    suspend fun getChapters(): List<Chapter>
}

