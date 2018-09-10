package com.astalos.locationregistry.di.components

import com.astalos.locationregistry.di.modules.ApplicationModule
import com.astalos.locationregistry.di.modules.ViewModelModule
import com.astalos.locationregistry.presentation.RegistryApplication
import com.astalos.locationregistry.presentation.view.MainActivity
import com.astalos.locationregistry.presentation.view.fragments.location.LocationsFragment
import com.astalos.locationregistry.presentation.view.fragments.location.SaveLocationDialogFragment
import com.astalos.locationregistry.presentation.view.fragments.user.SaveUserDialogFragment
import com.astalos.locationregistry.presentation.view.fragments.user.UsersFragment
import dagger.Component
import javax.inject.Singleton

/**
 * @author Tomasz Czura on 9/4/18.
 */
@Singleton
@Component(modules = [ ApplicationModule::class, ViewModelModule::class ])
interface ApplicationComponent {
    fun inject(application: RegistryApplication)
    fun inject(application: UsersFragment)
    fun inject(locationsFragment: LocationsFragment)
    fun inject(saveUserDialogFragment: SaveUserDialogFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(saveLocationDialogFragment: SaveLocationDialogFragment)
}