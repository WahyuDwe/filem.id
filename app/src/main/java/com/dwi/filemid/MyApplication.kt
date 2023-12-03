package com.dwi.filemid

import android.app.Application
import com.dwi.filemid.di.useCaseModule
import com.dwi.filemid.di.viewModelModule
import com.dwi.filmid.core.di.databaseModule
import com.dwi.filmid.core.di.networkModule
import com.dwi.filmid.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}