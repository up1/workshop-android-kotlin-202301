package com.example.demoday2

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.AfterClass

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.BeforeClass

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @BeforeClass
    fun init() {
        // Write utl to shared preferences
    }

    @AfterClass
    fun reset() {
        // Remove data from shared preference
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry
            .getInstrumentation().targetContext



        assertEquals("com.example.demoday2", appContext.packageName)
    }
}