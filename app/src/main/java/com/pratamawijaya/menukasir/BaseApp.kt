package com.pratamawijaya.menukasir

import android.app.Application
import com.pratamawijaya.menukasir.di.appModule
import org.koin.android.ext.android.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}