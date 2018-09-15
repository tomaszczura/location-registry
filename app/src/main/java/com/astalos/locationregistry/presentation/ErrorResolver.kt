package com.astalos.locationregistry.presentation

import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.repository.Failure

/**
 * @author Tomasz Czura on 9/13/18.
 */
object ErrorResolver {
    fun getErrorResId(error: Failure): Int = when (error) {
        is Failure.GPSDisabled -> R.string.gps_disabled_error
        is Failure.PermissionDeniedFailure -> R.string.permission_denied
        else -> R.string.unknown_error
    }
}
