package com.astalos.locationregistry.presentation.view.activities

import android.support.v7.app.AppCompatActivity
import com.astalos.locationregistry.di.components.ActivityComponent
import com.astalos.locationregistry.di.components.DaggerActivityComponent
import com.astalos.locationregistry.di.modules.ActivityModule
import com.astalos.locationregistry.presentation.RegistryApplication

/**
 * @author Tomasz Czura on 9/13/18.
 */
abstract class BaseActivity: AppCompatActivity() {

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent
                .builder()
                .applicationComponent((application as RegistryApplication).appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }
}