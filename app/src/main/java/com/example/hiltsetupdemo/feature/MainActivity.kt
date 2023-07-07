package com.example.hiltsetupdemo.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.hiltsetupdemo.LoginRepository
import com.example.hiltsetupdemo.MainRepository
import com.example.hiltsetupdemo.adapter.MainAdapter
import com.example.hiltsetupdemo.adapterdelegates.SimpleButtonEventHandler
import com.example.hiltsetupdemo.adapterdelegates.SimpleButtonRowUiItem
import com.example.hiltsetupdemo.adapterdelegates.model.RowUiItem
import com.example.hiltsetupdemo.annotations.DataAnnotation
import com.example.hiltsetupdemo.databinding.ActivityMainBinding
import com.example.hiltsetupdemo.helpers.ListUpdateHelper
import com.example.hiltsetupdemo.reload.ReloadHelper
import com.example.hiltsetupdemo.reload.ReloadScreenComponent
import com.example.hiltsetupdemo.utilities.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewBinding by viewBinding(ActivityMainBinding::inflate)
    private var viewModel: MainViewModel? = null

    @Inject
    lateinit var mainRepository: MainRepository

    @Inject
    lateinit var listUpdateHelper: ListUpdateHelper

    @Inject
    lateinit var reloadHelper: ReloadHelper

    @Inject
    @DataAnnotation
    lateinit var loginRepository: LoginRepository

    private val adapter: ListDelegationAdapter<List<RowUiItem>> by lazy {
        MainAdapter(simpleButtonEventHandler)
    }

    private val simpleButtonEventHandler = object : SimpleButtonEventHandler {
        override fun onButtonClicked(item: SimpleButtonRowUiItem) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainRepository.printLog("Main Activity")
        loginRepository.login("User", "Password")

        viewBinding.recycler.adapter = adapter
        listUpdateHelper.observe(
            viewLifecycleOwner = this,
            interactionHost = viewModel!!,
            recyclerView = viewBinding.recycler,
            adapter = adapter
        )

    }

    override fun onResume() {
        super.onResume()
        if (reloadHelper.shouldReload(ReloadScreenComponent.ONE)){
            // Do the need full
            reloadHelper.consumeReloadComponent(ReloadScreenComponent.ONE)
        }
    }
}