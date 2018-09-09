package com.astalos.locationregistry.presentation.view.fragments.user

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.domain.entities.User
import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.presentation.extensions.textValue
import com.astalos.locationregistry.presentation.view.fragments.BaseDialogFragment
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.fragment_add_user.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * @author Tomasz Czura on 9/9/18.
 */
class SaveUserDialogFragment : BaseDialogFragment() {
    override val layoutId = R.layout.fragment_add_user

    @Inject lateinit var viewModel: UsersViewModel

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

        appComponent.inject(this)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveBtn.onClick { onSaveClick() }
        cancelBtn.onClick { dismiss() }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)[UsersViewModel::class.java]
    }

    private fun onSaveClick() {
        val user = arguments?.getParcelable<User>(USER_ARG)?.copy(name = userName.textValue()) ?: User(name = userName.textValue())
        viewModel.saveUser(user, ::handleUserSave , ::handleError)
    }

    private fun handleUserSave(user: User) {
        toast(R.string.user_created).show()
        dismiss()
    }

    private fun handleError(failure: Failure) {
        toast(R.string.unknown_error).show()
    }

}