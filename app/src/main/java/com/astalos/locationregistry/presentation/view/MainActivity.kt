package com.astalos.locationregistry.presentation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.astalos.locationregistry.R
import com.astalos.locationregistry.presentation.extensions.doTransaction
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Tomasz Czura on 9/7/18.
 */
class MainActivity : AppCompatActivity() {

    private val locationsFrgment by lazy {
        supportFragmentManager.findFragmentById(R.id.locationsFragment)
    }

    private val usersFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.usersFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLocationsFragment()
        initNavigation()
    }

    private fun initNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.locations_list -> showLocationsFragment()
                R.id.users_list -> showUsersFragment()
            }
            true
        }
    }

    private fun showLocationsFragment() {
        supportFragmentManager.doTransaction {
            show(locationsFragment)
            hide(usersFragment)
        }
    }

    private fun showUsersFragment() {
        supportFragmentManager.doTransaction {
            show(usersFragment)
            hide(locationsFragment)
        }
    }
}