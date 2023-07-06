package com.example.hiltsetupdemo.utilities

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * This is customised extension function class to observe the values
 */

inline fun <T> Flow<T>.collect(
    viewLifecycleOwner: LifecycleOwner,
    crossinline onCollect: suspend (T) -> Unit
) = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
    collect {
        onCollect(it)
    }
}

inline fun <T> Flow<T>.collectResumed(
    viewLifecycleOwner: LifecycleOwner,
    crossinline onCollect: suspend (T) -> Unit
) = viewLifecycleOwner.lifecycleScope.launchWhenResumed {
    collect {
        onCollect(it)
    }
}


inline fun <T> Flow<T>.collectLatest(
    viewLifecycleOwner: LifecycleOwner,
    crossinline onCollect: suspend (T) -> Unit
) = viewLifecycleOwner.lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collectLatest {
            onCollect(it)
        }
    }
}
