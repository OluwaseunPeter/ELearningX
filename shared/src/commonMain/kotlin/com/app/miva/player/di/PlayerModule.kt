package com.app.miva.player.di

import com.app.miva.db.LessonNoteTableQueries
import com.app.miva.lessons.data.dao.LessonNoteDaoImpl
import com.app.miva.player.domain.dao.LessonNoteDao
import com.app.miva.player.domain.usecase.GetLessonNotesUseCase
import com.app.miva.player.domain.usecase.SaveLessonNoteUseCase
import com.app.miva.player.presentation.viewmodel.PlayerViewmodel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val playerModule = module {
    single { LessonNoteTableQueries(get()) }
    single<LessonNoteDao> { LessonNoteDaoImpl(query = get()) }
    single { GetLessonNotesUseCase(get()) }
    single { SaveLessonNoteUseCase(get()) }
    viewModelOf(::PlayerViewmodel)
}