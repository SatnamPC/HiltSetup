package com.example.hiltsetupdemo.reload

/**  How to use Reload Helper
 @Inject
 lateinit var reloadHelper: ReloadHelper

 ---- To add the reloading component ----
 reloadHelper.addReloadComponent(ReloadScreenComponent.ONE)

 ---- To check and consume the reloading component ----
 if (reloadHelper.shouldReload(ReloadScreenComponent.ONE)){
 // Need to call what you want to execute
 reloadHelper.consumeReloadComponent(ReloadScreenComponent.ONE)
 }
 */


/** This interface will help in the reload the component */
interface ReloadHelper {
    /** This function will use to add screen component to reload */
    fun addReloadComponent(component: ReloadScreenComponent)

    /** This function will use to remove screen component after reload */
    fun consumeReloadComponent(component: ReloadScreenComponent)

    /** This function will use to check screen component need to reload */
    fun shouldReload(component: ReloadScreenComponent): Boolean
}

enum class ReloadScreenComponent {
    ONE, TWO, THREE
}