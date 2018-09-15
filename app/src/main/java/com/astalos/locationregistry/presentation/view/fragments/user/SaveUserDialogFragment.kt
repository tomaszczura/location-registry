package com.astalos.locationregistry.presentation.view.fragments.user

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.presentation.ErrorResolver
import com.astalos.locationregistry.presentation.extensions.textValue
import com.astalos.locationregistry.presentation.view.fragments.BaseDialogFragment
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.dialog_buttons.*
import kotlinx.android.synthetic.main.fragment_add_user.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

/**
 * @author Tomasz Czura on 9/9/18.
 */
class SaveUserDialogFragment : BaseDialogFragment() {
    override val layoutId = R.layout.fragment_add_user

    private lateinit var viewModel: UsersViewModel

    private var passedUser: User? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent.inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        passedUser = arguments?.getParcelable(USER_ARG)
        saveBtn.onClick { onSaveClick() }
        cancelBtn.onClick { dismiss() }
        userName.setText(passedUser?.name ?: "")
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UsersViewModel::class.java]
    }

    private fun onSaveClick() {
        if (!userName.textValue().isBlank()) {
            val user = passedUser?.copy(name = userName.textValue()) ?: User(name = userName.textValue())
            viewModel.saveUser(user, ::handleUserSave, ::handleError)
        }
    }

    private fun handleUserSave(user: User) {
        toast(R.string.user_saved).show()
        dismiss()
    }

    private fun handleError(failure: Failure) {
        toast(ErrorResolver.getErrorResId(failure)).show()
    }
}