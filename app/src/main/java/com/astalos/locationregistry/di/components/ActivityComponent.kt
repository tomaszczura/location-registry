package com.astalos.locationregistry.di.components

import com.astalos.locationregistry.di.PerActivity
import com.astalos.locationregistry.di.modules.ActivityModule
import com.astalos.locationregistry.di.modules.RepositoriesModule
import com.astalos.locationregistry.di.modules.ViewModelModule
import com.astalos.locationregistry.presentation.view.activities.MainActivity
import com.astalos.locationregistry.presentation.view.fragments.location.LocationsFragment
import com.astalos.locationregistry.presentation.view.fragments.location.SaveLocationDialogFragment
import com.astalos.locationregistry.presentation.view.fragments.user.SaveUserDialogFragment
import com.astalos.locationregistry.presentation.view.fragments.user.UsersFragment
import dagger.Component

/**
 * @author Tomasz Czura on 9/13/18.
 */
@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class, ViewModelModule::class, RepositoriesModule::class])
interface ActivityComponent {
    fun inject(application: UsersFragment)
    fun inject(locationsFragment: LocationsFragment)
    fun inject(saveUserDialogFragment: SaveUserDialogFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(saveLocationDialogFragment: SaveLocationDialogFragment)
}