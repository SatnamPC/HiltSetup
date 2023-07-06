package com.example.hiltsetupdemo.feature

import androidx.lifecycle.ViewModel
import com.example.hiltsetupdemo.adapterdelegates.SimpleButtonRowUiItem
import com.example.hiltsetupdemo.adapterdelegates.model.RowUiItem
import com.example.hiltsetupdemo.utilities.ViewModelInteractionHelper
import com.example.hiltsetupdemo.utilities.ViewModelListInteraction
import com.example.hiltsetupdemo.utilities.ViewModelListInteractionHost
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    listHelper: ViewModelInteractionHelper<RowUiItem>
) : ViewModel(),
    ViewModelListInteraction<RowUiItem> by listHelper,
    ViewModelListInteractionHost<RowUiItem> by listHelper {

    init {
        prepareScreen()
    }

    /**
     * Prepare the screen to show on recyclerview
     */
    private fun prepareScreen() {
        list += SimpleButtonRowUiItem(
            id = "",
            title = "Main Button",
            isEnable = true,
        )

        notifyDataSetChanged()
    }
}