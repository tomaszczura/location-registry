package com.astalos.locationregistry.external.gpsLocationProvider

import android.Manifest
import android.app.Activity
import android.content.Context
import android.location.LocationManager
import com.astalos.locationregistry.domain.ILocationProvider
import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.interactor.OneOf
import com.astalos.locationregistry.domain.repository.Failure
import com.karumi.dexter.Dexter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import javax.inject.Inject
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * @author Tomasz Czura on 9/12/18.
 */
class GPSLocationProvider @Inject constructor(private val activity: Activity) : ILocationProvider {

    private var locationManager: LocationManager? = null
    private var locationListener: GPSLocationListener? = null

    override fun getLocation(): OneOf<Failure, SimpleLocation> = runBlocking {
        val grantedResult = getLocationPermissions()

        if (grantedResult.isError) {
            val error = (grantedResult as OneOf.Error<Failure>).error
            OneOf.Error(error)
        } else {
            getLocationFromGPS()
        }
    }

    private suspend fun getLocationPermissions(): OneOf<Failure, Boolean> = suspendCoroutine {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(PermissionsCallback(it))
                .check()
    }

    private suspend fun getLocationFromGPS(): OneOf<Failure, SimpleLocation> = suspendCoroutine {
        locationListener?.unsubscribe()
        locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager?.let { manager ->
            locationListener = GPSLocationListener(manager, it)
            launch(UI) {
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0.0f, locationListener)
            }
        }
    }

    fun cancel() {
        locationListener?.unsubscribe()
        locationListener = null
        locationManager = null
    }
}
