package com.shalomhalbert.popup.simplesafeapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI tests [MainActivity]
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule public val mainActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test fun headlineIsVisible(){
        onView(withId(R.id.headline)).check(matches(isDisplayed()))
    }

    @Test fun newGameButtonIsVisible(){
        onView(withId(R.id.newGameButton)).check(matches(isDisplayed()))
    }

    @Test fun headlinesShowsWin(){
        onView(withId(R.id.s00)).perform(click())//X1
        onView(withId(R.id.s23)).perform(click())//O
        onView(withId(R.id.s01)).perform(click())//X2
        onView(withId(R.id.s33)).perform(click())//O
        onView(withId(R.id.s02)).perform(click())//X3
        onView(withId(R.id.s12)).perform(click())//O
        onView(withId(R.id.s03)).perform(click())//X4

        onView(withId(R.id.headline)).check(matches(withText(R.string.headline_winner_x)))
    }
}