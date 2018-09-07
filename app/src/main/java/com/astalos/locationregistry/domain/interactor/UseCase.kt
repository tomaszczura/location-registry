package com.astalos.locationregistry.domain.interactor

import com.astalos.locationregistry.domain.repository.Failure
import com.astalos.locationregistry.domain.repository.OneOf
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * @author Tomasz Czura on 9/4/18.
 */
abstract class UseCase<out Type, in Params> {

    abstract suspend fun run(params: Params): OneOf<Failure, Type>

    fun execute(params: Params, onResult: (OneOf<Failure, Type>) -> Unit) {
        val job = async(CommonPool) { run(params) }
        launch(UI) { onResult.invoke(job.await()) }
    }

    open class NoParams
}
