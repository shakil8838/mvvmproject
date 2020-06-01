package xyz.xandsoft.mvvmproject.interfaces.authentication

import xyz.xandsoft.mvvmproject.data.db.Users

interface AuthStateListener {

    fun onAuthenticationStarted()
    fun onAuthenticationSuccess(users: Users)
    fun onAuthenticationFailed(message: String)
}