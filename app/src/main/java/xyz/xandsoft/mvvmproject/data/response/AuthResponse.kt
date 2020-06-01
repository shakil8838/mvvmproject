package xyz.xandsoft.mvvmproject.data.response

import xyz.xandsoft.mvvmproject.data.db.Users

data class AuthResponse(

    val isSuccessful: Boolean?,
    val message: String?,
    val user: Users?
)