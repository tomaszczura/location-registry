package com.astalos.locationregistry.utils

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.astalos.locationregistry.TestRegistryApplication

/**
 * @author Tomasz Czura on 9/15/18.
 */

class TestAppRunner: AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestRegistryApplication::class.java.name, context)
    }
}
