package com.astalos.locationregistry.presentation.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.astalos.locationregistry.R
import com.astalos.locationregistry.di.components.ApplicationComponent
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.presentation.RegistryApplication
import com.astalos.locationregistry.presentation.extensions.doTransaction
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/7/18.
 */
class MainActivity : AppCompatActivity() {

    val appComponent: ApplicationComponent by lazy {
        (application as RegistryApplication).appComponent
    }

    private val locationsFrgment by lazy {
        supportFragmentManager.findFragmentById(R.id.locationsFragment)
    }

    private val usersFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.usersFragment)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        initNavigation()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[UsersViewModel::class.java]
        viewModel.users.observe(this, Observer<List<User>> { showUsers(it ?: emptyList()) })
        viewModel.error.observe(this, Observer<Failure> { handleError(it) })
    }

    private fun showUsers(list: List<User>) {
        if (list.isEmpty()) {
            showUsersFragment()
            bottomNavigationView.selectedItemId = R.id.users_list
        } else {
            showLocationsFragment()
            bottomNavigationView.selectedItemId = R.id.locations_list
        }
        unsubscribeFromViewModel()
    }

    private fun unsubscribeFromViewModel() {
        viewModel.users.removeObservers(this)
        viewModel.error.removeObservers(this)
    }

    private fun handleError(it: Failure?) {
        toast(R.string.user_fetch_error).show()
        unsubscribeFromViewModel()
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