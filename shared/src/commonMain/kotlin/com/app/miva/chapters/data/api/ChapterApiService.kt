package com.app.miva.chapters.data.api

import com.app.miva.chapters.data.model.ChapterApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ChapterApiService {
    suspend fun getChapters(): List<ChapterApiResponse>?
}


internal class ChapterApiServiceImpl(private val httpClient: HttpClient) : ChapterApiService {
    override suspend fun getChapters(): List<ChapterApiResponse>? {
        return try {
           httpClient.get("chapters").body()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    companion object {
        fun provideChapterApiService(httpClient: HttpClient): ChapterApiService {
            return ChapterApiServiceImpl(httpClient)
        }
    }

}


