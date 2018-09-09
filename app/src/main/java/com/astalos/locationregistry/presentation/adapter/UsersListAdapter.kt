package com.astalos.locationregistry.presentation.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.presentation.extensions.inflate
import org.jetbrains.anko.find
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * @author Tomasz Czura on 9/9/18.
 */
class UsersListAdapter @Inject constructor(private val context: Context) : RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {

    var users: List<User> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    var activeUserId: Int by Delegates.observable(0) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
            UserViewHolder(parent.inflate(R.layout.user_row))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(activeUserId, users[position])
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val userName = view.find<TextView>(R.id.userName)
        private val isActiveBtn = view.find<RadioButton>(R.id.isActiveBtn)
        private val editUserBtn = view.find<ImageButton>(R.id.editUserBtn)

        fun bind(activeUserId: Int, user: User) {
            isActiveBtn.isChecked = activeUserId == user.id
            userName.text = user.name
        }
    }
}