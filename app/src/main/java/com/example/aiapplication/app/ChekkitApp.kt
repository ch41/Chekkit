package com.example.aiapplication.app

import android.app.Application
import com.example.di.initKoin

class ChekkitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }

}
