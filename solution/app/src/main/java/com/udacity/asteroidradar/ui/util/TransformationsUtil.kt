package com.udacity.asteroidradar.ui.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations

/**
 * A utility to create a [LiveData] which will be giving out a [source] value manipulated with a
 * [mapper] function. The difference between this function and [Transformations.map] is that this
 * function additionally allows to set a [defaultValue] until the source starts producing.
 *
 * @param A            The type of original source.
 * @param B            The type of transformation result.
 * @param source       The [LiveData] instance which acts as the source of values.
 * @param defaultValue The value to be used until the [source] starts emitting.
 * @param mapper       A function to transform values emitted from the source.
 *
 * @return A new [LiveData] instance to observe upon.
 */
fun <A, B> transformWithDefault(
    source: LiveData<A>,
    defaultValue: B,
    mapper: (A) -> B
): LiveData<B> {

    val mediatorLiveData = MediatorLiveData<B>()
    mediatorLiveData.postValue(defaultValue)

    mediatorLiveData.addSource(source) {
        mediatorLiveData.postValue(mapper(it))
    }

    return mediatorLiveData
}
