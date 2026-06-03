package com.example.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(
    app: Application,
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    androidContext(app)
    appDeclaration()
    modules(
        appModule,
        dataModule,
        repositoryModule,
        viewModelModule
    )
}