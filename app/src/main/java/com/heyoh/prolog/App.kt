package com.heyoh.prolog

import android.app.Application
import com.heyoh.prolog.module.loginModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        // get list of all modules
        val moduleList = listOf(loginModule)
        // start koin with the module list
        startKoin {
            modules(moduleList)
        }
    }
}