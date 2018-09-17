package com.astalos.locationregistry.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.UserLocation
import com.astalos.locationregistry.presentation.extensions.inflate
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * @author Tomasz Czura on 9/10/18.
 */
class LocationsListAdapter @Inject constructor() : RecyclerView.Adapter<LocationsListAdapter.LocationViewHolder>() {
    var locations: List<UserLocation> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder = LocationViewHolder(parent.inflate(R.layout.location_row))

    override fun getItemCount() = locations.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(locations[position])
    }

    public class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")

        val latitude = view.find<TextView>(R.id.latitude)
        val longitude = view.find<TextView>(R.id.longitude)
        val locationDate = view.find<TextView>(R.id.locationDate)

        fun bind(location: UserLocation) {
            latitude.text = itemView.context.getString(R.string.lat, location.latitude.toString())
            longitude.text = itemView.context.getString(R.string.lng, location.longitude.toString())
            locationDate.text = dateFormat.format(Date(location.time))
        }
    }
}