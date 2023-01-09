package com.example.demo.jobs

import com.example.demo.data.local.Demo
import com.example.demo.data.local.DemoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DemoRepository(val demoDao: DemoDao) {
    fun tryInsertData() {
        CoroutineScope(Dispatchers.IO).launch {
            println("========== Run worker ===========")
            demoDao.insertDemo(Demo("new data"))
        }
    }
}