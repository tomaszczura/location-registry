package com.astalos.locationregistry.presentation.extensions

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


/**
 * @author Tomasz Czura on 9/7/18.
 */
inline fun FragmentManager.doTransaction(actions: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().actions().commit()
