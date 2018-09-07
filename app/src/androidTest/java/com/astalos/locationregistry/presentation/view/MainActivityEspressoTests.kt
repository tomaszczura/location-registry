package com.astalos.locationregistry.presentation.view

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.astalos.locationregistry.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Tomasz Czura on 9/7/18.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTests {

    @Rule @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun show_location_fragment_on_Locations_menu_click() {
        onView(withId(R.id.locations_list)).perform(click())
        onView(withId(R.id.locationsFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun show_users_fragment_on_Users_menu_click() {
        onView(withId(R.id.users_list)).perform(click())
        onView(withId(R.id.usersFragment)).check(matches(isDisplayed()))
    }

}