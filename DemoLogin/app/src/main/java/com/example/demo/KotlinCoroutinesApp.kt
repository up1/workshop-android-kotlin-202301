package com.example.demo

import android.app.Application
import androidx.work.*
import androidx.work.ExistingPeriodicWorkPolicy.KEEP
import com.example.demo.jobs.MyDemoWork
import java.util.concurrent.TimeUnit

class KotlinCoroutinesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setupWorkManagerJob()
    }

    private fun setupWorkManagerJob() {
        val workManagerConfiguration = Configuration.Builder()
            .setWorkerFactory(MyDemoWork.Factory())
            .build()
        WorkManager.initialize(this, workManagerConfiguration)

         val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        // Specify that the work should attempt to run every minute
        val work = PeriodicWorkRequestBuilder<MyDemoWork>(1, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        // Enqueue it work WorkManager, keeping any previously scheduled jobs for the same
        // work.
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(MyDemoWork::class.java.name, KEEP, work)
    }
}