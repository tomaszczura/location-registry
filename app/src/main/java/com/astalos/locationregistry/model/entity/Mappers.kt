package com.astalos.locationregistry.model.entity

import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.entities.UserLocation

/**
 * @author Tomasz Czura on 9/11/18.
 */
fun User.toEntity() = UserEntity(id?.toLong(), name, isActive)
fun UserEntity.toUser() = User(this.id?.toInt(), name, isActive)

fun UserLocation.toEntity() = UserLocationEntity(id?.toLong(), latitude, longitude, time, userId.toLong())
fun UserLocationEntity.toUserLocation() = UserLocation(id?.toInt(), latitude, longitude, time, userId.toInt())