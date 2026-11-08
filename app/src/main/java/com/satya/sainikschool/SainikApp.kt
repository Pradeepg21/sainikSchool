package com.satya.sainikschool

import android.app.Application
import com.satya.sainikschool.alarm.AlarmScheduler
import com.satya.sainikschool.data.store.AppGraph

class SainikApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppGraph.init(this)
        AlarmScheduler.createChannel(this)
    }
}
