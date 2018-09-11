package com.astalos.locationregistry.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * @author Tomasz Czura on 9/10/18.
 */
@Entity
data class UserEntity(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "isActive") var isActive: Boolean = false
)