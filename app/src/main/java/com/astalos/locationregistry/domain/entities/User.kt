package com.astalos.locationregistry.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Tomasz Czura on 9/4/18.
 */
@Parcelize
data class User(var id: Int? = null, val name: String, var isActive: Boolean = false) : Parcelable