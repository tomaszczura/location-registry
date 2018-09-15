package com.astalos.locationregistry.domain.repository

/**
 * @author Tomasz Czura on 9/4/18.
 */
sealed class Failure {
    class UnknownFailure : Failure()
    class NoUserFailure : Failure()
    class ActiveUserRemove : Failure()
    class PermissionDeniedFailure: Failure()
    class GPSDisabled: Failure()
    class CancelFailure: Failure()

    abstract class DetailedFailure: Failure()
}