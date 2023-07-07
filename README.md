# HiltSetup
How to setup of Hilt Implementation within android studio  

plugins {
    id 'com.google.dagger.hilt.android'
}
dependencies {
     implementation "com.google.dagger:hilt-android:2.44"
     kapt "com.google.dagger:hilt-compiler:2.44"
    }


# Delegation property uses
    <!-- Just need to add these dependencies in gradle file then you will be able to use delegation properties -->
    implementation "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.2"
    implementation "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.2"
    implementation "com.hannesdorfmann:adapterdelegates4-pagination:4.3.2"

# Reload Helper
    <!-- It will be helpful to handle to reloading screen and respective data -->
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

# State Flow Uses
/**
 * ********** How to use *********
 * private val _navigation = PrivateEventStateFlow<Boolean>()
 * val navigation = _navigation.EventStateFlow()
 *
 * This class is used to make the communication between view model and fragment
 */