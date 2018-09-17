package com.astalos.locationregistry.di.modules

import com.astalos.locationregistry.presentation.view.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * @author Tomasz Czura on 9/13/18.
 */
@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}