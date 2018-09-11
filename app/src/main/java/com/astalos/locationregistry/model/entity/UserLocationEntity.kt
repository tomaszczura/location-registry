package com.astalos.locationregistry.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

/**
 * @author Tomasz Czura on 9/10/18.
 */
@Entity(foreignKeys = [
    ForeignKey(entity = UserEntity::class,
            parentColumns = [ "id" ],
            childColumns = [ "userId" ],
            onDelete = CASCADE)
])
data class UserLocationEntity(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") var longitude: Double,
    @ColumnInfo(name = "time") var time: Long,
    @ColumnInfo(name = "userId") var userId: Long
)