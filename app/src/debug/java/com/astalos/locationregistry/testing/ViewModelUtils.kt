package com.astalos.locationregistry.testing

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.astalos.locationregistry.di.ViewModelFactory
import com.astalos.locationregistry.presentation.viewmodel.LocationsViewModel
import com.astalos.locationregistry.presentation.viewmodel.UsersViewModel
import javax.inject.Provider

/**
 * @author Tomasz Czura on 9/17/18.
 */

object ViewModelUtils {
    fun <T : ViewModel> createFor(model: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(model.javaClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return model as T
                }
                throw IllegalArgumentException("unexpected model class $modelClass")
            }
        }
    }

    fun createFactoryForViewModels(usersViewModel: UsersViewModel, locationsViewModel: LocationsViewModel) : ViewModelFactory {
        val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
        creators.put(UsersViewModel::class.java, Provider { usersViewModel })
        creators.put(LocationsViewModel::class.java, Provider { locationsViewModel })
        return ViewModelFactory(creators)
    }
}