package com.example.hiltsetupdemo.adapterdelegates

import com.example.hiltsetupdemo.adapterdelegates.model.RowUiItem
import com.example.hiltsetupdemo.databinding.ItemSimpleButtonLayoutBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

/** To hold the Simple button details */
data class SimpleButtonRowUiItem(
    val id: String,
    val title: String,
    val isEnable: Boolean = false
): RowUiItem

interface SimpleButtonEventHandler {
    fun onButtonClicked(item: SimpleButtonRowUiItem)
}

fun simpleButtonDelegate(eventHandler: SimpleButtonEventHandler) =
    adapterDelegateViewBinding<SimpleButtonRowUiItem, RowUiItem, ItemSimpleButtonLayoutBinding>(
        { layoutInflater, parent ->
            ItemSimpleButtonLayoutBinding.inflate(layoutInflater, parent, false)
        }) {
        binding.simpleButton.setOnClickListener { eventHandler.onButtonClicked(item) }
        bind {
            binding.simpleButton.text = item.title
            binding.simpleButton.isEnabled = item.isEnable
        }
    }