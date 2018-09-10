package com.astalos.locationregistry.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.astalos.locationregistry.di.ViewModelFactory
import com.astalos.locationregistry.di.ViewModelKey
import com.astalos.locationregistry.presentation.viewmodel.LocationsViewModel
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Tomasz Czura on 9/9/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    internal abstract fun postUsersViewModel(viewModel: UsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LocationsViewModel::class)
    internal abstract fun postLocationsViewModel(viewModel: LocationsViewModel): ViewModel

}