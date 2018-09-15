package com.astalos.locationregistry.domain.interactor

import com.astalos.locationregistry.domain.repository.Failure
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * @author Tomasz Czura on 9/4/18.
 */
abstract class UseCase<out Type, in Params> {

    private var job: Deferred<OneOf<Failure, Type>>? = null

    abstract suspend fun run(params: Params): OneOf<Failure, Type>

    fun execute(params: Params, onResult: (OneOf<Failure, Type>) -> Unit) {
        job?.cancel()
        job = async(CommonPool) { run(params) }
        launch(UI) {
            val result = job!!.await()
            onResult(result)
        }
    }

    open fun cancel() {
        job?.cancel()
    }

    open class NoParams
}
