package com.example.hiltsetupdemo.adapter

import com.example.hiltsetupdemo.adapterdelegates.SimpleButtonEventHandler
import com.example.hiltsetupdemo.adapterdelegates.model.RowUiItem
import com.example.hiltsetupdemo.adapterdelegates.simpleButtonDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class MainAdapter(simpleButtonEventHandler: SimpleButtonEventHandler) :
    ListDelegationAdapter<List<RowUiItem>>(simpleButtonDelegate(simpleButtonEventHandler))