package com.app.miva.chapters.domain.usecase

import com.app.miva.chapters.domain.repository.ChapterRepository
import com.app.miva.chapters.domain.model.Chapter

class GetChaptersUseCase(private val repository: ChapterRepository) {
    suspend fun execute(): List<Chapter> = repository.getChapters()
}