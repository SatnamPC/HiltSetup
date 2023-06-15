package com.example.hiltsetupdemo

import android.util.Log
import javax.inject.Inject

const val TAG = "TAG"

class MainRepository @Inject constructor() {

    fun printLog(message: String){
        Log.d(TAG, message)
    }
}

interface LoginRepository {
    fun login(user: String, password: String)
}

class LoginRepositoryImpl: LoginRepository {
    override fun login(user: String, password: String) {
        Log.d(TAG, "$user $password")
    }
}

class DataRepositoryImpl: LoginRepository {
    override fun login(user: String, password: String) {
        Log.d(TAG,"Data Repository")
    }
}