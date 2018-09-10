package com.astalos.locationregistry.presentation.view.fragments.location

import android.os.Bundle
import android.view.View
import com.astalos.locationregistry.R
import com.astalos.locationregistry.presentation.view.fragments.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_buttons.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * @author Tomasz Czura on 9/10/18.
 */
class SaveLocationDialogFragment : BaseDialogFragment() {
    override val layoutId: Int = R.layout.fragment_add_location

    companion object {
        fun getInstance() : SaveLocationDialogFragment {
            val fragment = SaveLocationDialogFragment()
            val arguments = Bundle()
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveBtn.onClick { onSaveClick() }
        cancelBtn.onClick { dismiss() }
    }

    private fun onSaveClick() {
        dismiss()
    }

}