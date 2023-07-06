package com.example.hiltsetupdemo.helpers

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltsetupdemo.utilities.ListUpdateEvent
import com.example.hiltsetupdemo.utilities.ViewModelListInteractionHost
import com.example.hiltsetupdemo.adapterdelegates.model.RowUiItem
import com.example.hiltsetupdemo.utilities.collect
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter
import javax.inject.Inject

/**
 *  How to use :
 *  @Inject
    lateinit var listUpdateHelper: ListUpdateHelper
 *
 *  inside onCreate() method
 *
 *  listUpdateHelper.observe(
    viewLifecycleOwner = this,
    interactionHost = viewModel!!,
    recyclerView = viewBinding.recycler,
    adapter = adapter
    )

 This is a helper class which helps to observe and show updated details of recyclerview
 */
class ListUpdateHelper @Inject constructor() {

    fun observe(
        viewLifecycleOwner: LifecycleOwner,
        interactionHost: ViewModelListInteractionHost<RowUiItem>,
        recyclerView: RecyclerView,
        adapter: AbsDelegationAdapter<List<RowUiItem>>
    ) {
        interactionHost.items.collect(viewLifecycleOwner) {
            adapter.items = it
            recyclerView.post { adapter.notifyDataSetChanged() }
        }

        interactionHost.listUpdateEvent.collect(viewLifecycleOwner) { event ->
            when (event) {
                ListUpdateEvent.NotifyDataSetChanged -> {
                    recyclerView.post { adapter.notifyDataSetChanged() }
                    interactionHost.listUpdateConsumed()
                }
                is ListUpdateEvent.NotifyItemChanged -> {
                    recyclerView.post { adapter.notifyItemChanged(event.index) }
                    interactionHost.listUpdateConsumed()
                }
                null -> Unit
            }
        }
    }
}