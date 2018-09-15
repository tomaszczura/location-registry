package com.astalos.locationregistry.di.modules

import android.app.Activity
import android.content.Context
import com.astalos.locationregistry.di.PerActivity
import com.astalos.locationregistry.model.external.gpsLocationProvider.GPSLocationProvider
import dagger.Module
import dagger.Provides

/**
 * @author Tomasz Czura on 9/13/18.
 */
@Module
class ActivityModule(val activity: Activity) {
    @Provides
    @PerActivity
    fun provideActivity() = activity

    @Provides
    @PerActivity
    fun provideContext(): Context = activity

    @Provides
    @PerActivity
    fun providesGPSLocationProvider() = GPSLocationProvider(activity)
}