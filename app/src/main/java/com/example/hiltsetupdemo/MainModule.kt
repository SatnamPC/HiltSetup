package com.example.hiltsetupdemo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

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

}