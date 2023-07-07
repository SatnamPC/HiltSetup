package com.example.hiltsetupdemo

import com.example.hiltsetupdemo.annotations.DataAnnotation
import com.example.hiltsetupdemo.reload.ReloadHelper
import com.example.hiltsetupdemo.reload.ReloadHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MainModule {

    @Provides
    @Named("login")
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }

    @Provides
    @DataAnnotation
    fun provideDataRepository(): LoginRepository {
        return DataRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideAppReloadHelper(): ReloadHelper = ReloadHelperImpl()

}