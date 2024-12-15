package com.app.miva.chapters.di

import com.app.miva.chapters.data.api.ChapterApiService
import com.app.miva.chapters.data.api.ChapterApiServiceImpl
import com.app.miva.chapters.data.repository.ChapterRepositoryImpl
import com.app.miva.chapters.domain.repository.ChapterRepository
import com.app.miva.chapters.domain.usecase.GetChaptersUseCase
import com.app.miva.chapters.presentation.viewmodel.ChaptersViewmodel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chapterModule = module {
    single<ChapterApiService> { ChapterApiServiceImpl.provideChapterApiService(get()) }
    single<ChapterRepository> { ChapterRepositoryImpl(get()) }
    factory { GetChaptersUseCase(get()) }
    viewModelOf(::ChaptersViewmodel)
}