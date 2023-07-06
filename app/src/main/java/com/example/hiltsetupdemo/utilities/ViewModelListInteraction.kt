package com.example.hiltsetupdemo.utilities

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/** Helps interaction with view models that contains a list */
interface ViewModelListInteraction<T> {
    /** Provide the list of items that are used */
    val list: MutableList<T>

    fun notifyDataSetChanged()

    fun notifyItemsChanges(position: Int)
}

/**
 * Interaction of the host view model with a list
 * This interface can help view models to expose state flows related to lists.
 *
 * @param T
 */
interface ViewModelListInteractionHost<T>{
    val _items: MutableStateFlow<List<T>>

    /** Provide a state flow for the items of the list */
    val items: StateFlow<List<T>>

    /** A state flow for the updates of the list */
    val listUpdateEvent: StateFlow<ListUpdateEvent?>

    /**
     * Function to consume [listUpdateEvent] event
     */
    fun listUpdateConsumed()
}

sealed class ListUpdateEvent {
    data class NotifyItemChanged(val index: Int) : ListUpdateEvent()

    object NotifyDataSetChanged: ListUpdateEvent()
}