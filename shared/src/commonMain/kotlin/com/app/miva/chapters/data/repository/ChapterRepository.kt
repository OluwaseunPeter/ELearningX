package com.app.miva.chapters.data.repository

import com.app.miva.chapters.data.api.ChapterApiService
import com.app.miva.chapters.data.model.ChapterApiResponse
import com.app.miva.chapters.data.model.toDomain
import com.app.miva.chapters.domain.model.Chapter

interface ChapterRepository {
    suspend fun getChapters(): List<Chapter>
}

internal class ChapterRepositoryImpl(private val apiService: ChapterApiService) : ChapterRepository {
    override suspend fun getChapters(): List<Chapter> {
        return apiService.getChapters()?.map(ChapterApiResponse::toDomain).orEmpty()
    }
}