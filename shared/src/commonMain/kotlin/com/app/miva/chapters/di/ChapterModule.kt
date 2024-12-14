package com.app.miva.chapters.di

import com.app.miva.chapters.data.api.ChapterApiService
import com.app.miva.chapters.data.api.ChapterApiServiceImpl
import com.app.miva.chapters.data.repository.ChapterRepository
import com.app.miva.chapters.data.repository.ChapterRepositoryImpl
import com.app.miva.chapters.domain.usecase.GetChaptersUseCase
import com.app.miva.chapters.presentation.viewmodel.ChaptersViewmodel
import com.orda.shared.data.api.getDefaultClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val chapterModule = module {
    single { getDefaultClient() }
    single<ChapterApiService> { ChapterApiServiceImpl.provideChapterApiService(get()) }
    single<ChapterRepository> { ChapterRepositoryImpl(get()) }
    factory { GetChaptersUseCase(get()) }
    viewModel { ChaptersViewmodel(get()) }
}