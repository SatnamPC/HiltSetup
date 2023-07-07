package com.example.hiltsetupdemo.utilities

import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ********** How to use *********
 * private val _navigation = PrivateEventStateFlow<Boolean>()
 * val navigation = _navigation.EventStateFlow()
 *
 * This class is used to make the communication between view model and fragment
 */
class PrivateEventStateFlow<T> {
    private val _event = MutableStateFlow<T?>(null)
    val event = EventStateFlow()

    /** To set the values from view model */
    fun setValue(value: T) {
        _event.value = value
    }

    inner class EventStateFlow {
        /** State flow */
        private val eventFlow = _event.asStateFlow()
        private fun eventConsumed() {
            _event.value = null
        }

        /** Collect event and consume it automatically */
        fun collectEvent(
            owner: LifecycleOwner,
            onCollect: suspend (T) -> Unit
        ) {
            eventFlow.collect(owner) {
                if (it == null) return@collect
                eventConsumed()
                onCollect(it)
            }
        }
    }
}