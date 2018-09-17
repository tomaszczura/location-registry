package com.astalos.locationregistry.domain.interactor

import com.astalos.locationregistry.domain.ILocationProvider
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.entities.UserLocation

/**
 * @author Tomasz Czura on 9/4/18.
 */
class UserIdParams(val userId: Int)
class UserParams(val user: User)
class UserLocationParams(val location: UserLocation)
class LocationIdParams(val locationId: Int)
class GetCurrentLocationParams(val locationProvider: ILocationProvider)