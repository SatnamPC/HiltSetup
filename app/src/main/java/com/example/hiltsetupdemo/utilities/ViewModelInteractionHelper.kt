package com.example.hiltsetupdemo.utilities

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Helper that keeps a list and implements interfaces for the interaction of this list
 */
class ViewModelInteractionHelper<T> @Inject constructor() :
    ViewModelListInteraction<T>,
    ViewModelListInteractionHost<T> {
    override val list = mutableListOf<T>()

    override val _items = MutableStateFlow(emptyList<T>())
    override val items = _items.asStateFlow()

    private val _listUpdateEvent = MutableStateFlow<ListUpdateEvent?>(null)
    override val listUpdateEvent = _listUpdateEvent.asStateFlow()

    override fun notifyDataSetChanged() {
        _items.value = list.toList()
    }

    override fun notifyItemsChanges(position: Int) {
        _listUpdateEvent.value = ListUpdateEvent.NotifyItemChanged(position)
    }

    override fun listUpdateConsumed() {
        _listUpdateEvent.value = null
    }
}
