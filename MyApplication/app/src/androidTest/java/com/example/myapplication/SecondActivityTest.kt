package com.example.myapplication

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SecondActivityTest {

    @Rule
    @JvmField
    var rule = ActivityScenarioRule(SecondActivity::class.java)

    @Test
    fun successWithOpenSecondActivity() {

    }


}