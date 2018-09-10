package com.astalos.locationregistry.presentation.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText


/**
 * @author Tomasz Czura on 9/9/18.
 */
fun EditText.textValue() = this.text.toString()

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    if (isVisible) {
        show()
    } else {
        gone()
    }
}