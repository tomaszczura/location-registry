package com.astalos.locationregistry.presentation.view

import android.support.design.widget.BottomNavigationView
import com.astalos.locationregistry.R
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldNotBeNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


/**
 * @author Tomasz Czura on 9/7/18.
 */
@RunWith(RobolectricTestRunner::class)
class MainActivityTests {

    @Test
    fun `should have bottom navigation`() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val navigation = activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        navigation?.shouldNotBeNull()
    }

    @Test
    fun `bottom navigation should have Locations entry`() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val navigation = activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val menu = navigation.menu.findItem(R.id.locations_list)

        menu?.shouldNotBeNull()
    }

    @Test
    fun `bottom navigation should have Users entry`() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val navigation = activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val menu = navigation.menu.findItem(R.id.users_list)

        menu?.shouldNotBeNull()
    }

    @Test
    fun `should show locations fragment on start`() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val locationsFragment = activity.supportFragmentManager.findFragmentById(R.id.locationsFragment)
        locationsFragment.isVisible shouldBe true
    }

    @Test
    fun `should hide users fragment on start`() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val usersFragment = activity.supportFragmentManager.findFragmentById(R.id.usersFragment)
        usersFragment.isVisible shouldBe false
    }
}