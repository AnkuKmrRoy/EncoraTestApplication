package com.encora.encoratestapplication

import android.app.Application
import com.leopold.mvvm.di.roomModule
import com.leopold.mvvm.di.viewModelModule
import org.koin.android.ext.android.startKoin

class EncoraAssignmentApplication : Application() {

    companion object {
        lateinit var instance: EncoraAssignmentApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            roomModule,
            viewModelModule
        ))
    }
}