package com.astalos.locationregistry.model.database

import android.content.Context
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.ILocationsRepository
import com.astalos.locationregistry.domain.repository.OneOf
import com.astalos.locationregistry.model.entity.toEntity
import com.astalos.locationregistry.model.entity.toUserLocation
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/11/18.
 */
class DatabaseLocationRepository @Inject constructor(private val context: Context) : ILocationsRepository {
    private val database by lazy { AppDatabase.getInstance(context) }

    override fun locations(userId: Int): OneOf<Failure, List<UserLocation>> =
            OneOf.Success(database.userLocationDao.getAll(userId.toLong()).map { it.toUserLocation() })

    override fun addLocation(location: UserLocation): OneOf<Failure, UserLocation> {
        val locationId = database.userLocationDao.saveLocation(location.toEntity())
        location.id = locationId.toInt()
        return OneOf.Success(location)
    }

}