package com.example.astalos.locationregistry.domain.interactor

import com.example.astalos.locationregistry.domain.repository.Failure
import com.example.astalos.locationregistry.domain.repository.OneOf
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * @author Tomasz Czura on 9/4/18.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params?): OneOf<Failure, Type>

    fun execute(params: Params?, onResult: (OneOf<Failure, Type>) -> Unit) {
        val job = async(CommonPool) { run(params) }
        launch(UI) { onResult.invoke(job.await()) }
    }

    fun execute(onResult: (OneOf<Failure, Type>) -> Unit) {
        execute(null, onResult)
    }
}