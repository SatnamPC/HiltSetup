package com.example.hiltsetupdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application() {

    @Inject
    lateinit var mainRepository: MainRepository

    override fun onCreate() {
        super.onCreate()

        mainRepository.printLog("Test Message")
    }
}