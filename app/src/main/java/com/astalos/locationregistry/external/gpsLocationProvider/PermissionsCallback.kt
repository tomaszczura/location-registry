package com.astalos.locationregistry.external.gpsLocationProvider

import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.interactor.OneOf
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlin.coroutines.experimental.Continuation

/**
 * @author Tomasz Czura on 9/13/18.
 */
class PermissionsCallback(private val task: Continuation<OneOf<Failure, Boolean>>) : BasePermissionListener() {
    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        task.resume(OneOf.Success(true))
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        task.resume(OneOf.Error(Failure.PermissionDeniedFailure()))
    }
}
