package com.astalos.locationregistry.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.astalos.locationregistry.domain.repository.Failure

/**
 * @author Tomasz Czura on 9/7/18.
 */
open class BaseViewModel : ViewModel() {

    var error: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleError(error: Failure) {
        this.error.value = error
    }
}