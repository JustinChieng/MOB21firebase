package com.justin.mob21justin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {
    companion object {
      init {
         System.loadLibrary("mob21justin")
      }
    }
}