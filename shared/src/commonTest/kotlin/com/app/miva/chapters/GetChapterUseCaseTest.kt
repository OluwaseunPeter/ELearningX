package com.app.miva.chapters

import com.app.miva.chapters.domain.usecase.GetChapterLessonsUseCase
import com.app.miva.core.di.initKoin
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class GetChapterUseCaseTest : KoinComponent{

    init {
        initKoin()
    }

    private val usecase : GetChapterLessonsUseCase by inject()


    @Test
    fun testGetUseCase() = runBlocking {
        val res = usecase.execute()
//        println("UseCase ${res}" )
        assertEquals(1 , res.size)
        assertEquals(5 , res.first().lessons.size)
    }
}