package com.astalos.locationregistry.presentation.view.fragments.user

import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.presentation.view.fragments.BaseDialogFragment
import kotlinx.android.synthetic.main.fragment_add_user.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * @author Tomasz Czura on 9/9/18.
 */
class SaveUserDialogFragment : BaseDialogFragment() {
    override val layoutId = R.layout.fragment_add_user

    companion object {
        private const val USER_ARG = "user_arg"

        fun getInstance(user: User? = null): SaveUserDialogFragment {
            val fragment = SaveUserDialogFragment()
            val arguments = Bundle()
            arguments.putParcelable(USER_ARG, user)
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancelBtn.onClick { dismiss() }
    }

}