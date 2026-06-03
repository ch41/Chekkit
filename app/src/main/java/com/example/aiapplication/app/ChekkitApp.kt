package com.example.aiapplication.app

import android.app.Application
import com.example.data.ocr.TesseractDataInitializer
import com.example.di.initKoin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChekkitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(this)
        //remove, just for test
        GlobalScope.launch {
            val initializer = TesseractDataInitializer(this@ChekkitApp)
            initializer.initialize()
        }
    }

}