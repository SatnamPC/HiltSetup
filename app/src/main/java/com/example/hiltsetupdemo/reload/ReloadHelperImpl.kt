package com.example.hiltsetupdemo.reload

class ReloadHelperImpl : ReloadHelper {
    private val _reloadList = mutableListOf<ReloadScreenComponent>()
    override fun addReloadComponent(component: ReloadScreenComponent) {
        _reloadList.add(component)
    }

    override fun consumeReloadComponent(component: ReloadScreenComponent) {
        _reloadList.remove(component)
    }

    override fun shouldReload(component: ReloadScreenComponent): Boolean {
        return _reloadList.contains(component)
    }
}