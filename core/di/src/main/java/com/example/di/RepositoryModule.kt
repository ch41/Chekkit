package com.example.di

import com.example.data.ocr.TesseractEngine
import com.example.data.repository.DashboardRepositoryImpl
import com.example.data.repository.ImageRepositoryImpl
import com.example.domain.repository.DashboardRepository
import com.example.domain.repository.ImageRepository
import com.example.domain.use_case.GetDashboardDataUseCase
import com.example.domain.use_case.TextExtractionUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { TesseractEngine(androidContext()) }
    single<ImageRepository> { ImageRepositoryImpl(get()) }
    single<TextExtractionUseCase> { TextExtractionUseCase(get()) }

    single<DashboardRepository> { DashboardRepositoryImpl() }
    single { GetDashboardDataUseCase(get()) }
}
