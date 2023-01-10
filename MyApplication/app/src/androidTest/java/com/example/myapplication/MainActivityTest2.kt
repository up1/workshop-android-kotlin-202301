package com.example.myapplication


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val button = onView(
            allOf(
                withId(R.id.button), withText("BUTTON"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val editText = onView(
            allOf(
                withId(R.id.hello_text), withText("Name"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        editText.check(matches(withText("Name")))

        val textView = onView(
            allOf(
                withText("My Application"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.action_bar),
                        withParent(withId(com.google.android.material.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("My Application")))

        val textView2 = onView(
            allOf(
                withText("My Application"),
                withParent(
                    allOf(
                        withId(com.google.android.material.R.id.action_bar),
                        withParent(withId(com.google.android.material.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("My Application")))

        val materialButton = onView(
            allOf(
                withId(R.id.button), withText("Button"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
