package com.example.demo.jobs

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.demo.data.local.getDatabase


class MyDemoWork(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = DemoRepository(database.demoDao)

        return try {
            repository.tryInsertData()
            Result.success()
        } catch (error: Error) {
            Result.failure()
        }
    }

    class Factory() : WorkerFactory() {
        override fun createWorker(appContext: Context, workerClassName: String, workerParameters: WorkerParameters): ListenableWorker? {
            return MyDemoWork(appContext, workerParameters)
        }

    }
}