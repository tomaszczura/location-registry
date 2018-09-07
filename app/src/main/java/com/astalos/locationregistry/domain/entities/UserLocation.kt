package com.astalos.locationregistry.domain.entities

/**
 * @author Tomasz Czura on 9/4/18.
 */
data class UserLocation(val id: Int?, val latitude: Double, val longitude: Double, val time: Long, val userId: Int)