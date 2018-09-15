package com.astalos.locationregistry.model.external.gpsLocationProvider

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import com.astalos.locationregistry.domain.entities.SimpleLocation
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.interactor.OneOf
import kotlin.coroutines.experimental.Continuation

/**
 * @author Tomasz Czura on 9/13/18.
 */
class GPSLocationListener(private val locationManager: LocationManager, private val task: Continuation<OneOf<Failure, SimpleLocation>>) : LocationListener {
    override fun onLocationChanged(location: Location?) {
        val simpleLocation = SimpleLocation(location?.latitude ?: 0.0, location?.longitude
                ?: 0.0)
        locationManager.removeUpdates(this)
        task.resume(OneOf.Success(simpleLocation))
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderEnabled(provider: String?) {}

    override fun onProviderDisabled(provider: String?) {
        locationManager.removeUpdates(this)
        task.resume(OneOf.Error(Failure.GPSDisabled()))
    }

    fun unsubscribe() {
        locationManager.removeUpdates(this)
        try {
            task.resume(OneOf.Error(Failure.CancelFailure()))
        } catch (e: Exception) {
            Log.d("GPSLocationListener", "Already resumed")
        }
    }
}