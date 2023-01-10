package com.example.demoday2.data.local

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