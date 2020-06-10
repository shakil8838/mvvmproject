package xyz.xandsoft.mvvmproject.repositories

import retrofit2.Response
import xyz.xandsoft.mvvmproject.data.db.AppDatabase
import xyz.xandsoft.mvvmproject.data.db.UserDAO
import xyz.xandsoft.mvvmproject.data.db.Users
import xyz.xandsoft.mvvmproject.data.response.ApiResponse
import xyz.xandsoft.mvvmproject.data.response.AuthResponse
import xyz.xandsoft.mvvmproject.interfaces.network.NetworkCall

class AuthenticationRepository(
    private val api: NetworkCall,
    private val db: AppDatabase
) : ApiResponse() {

    suspend fun loginFunction(email: String, pass: String): AuthResponse {
        return apiResponse {
            api.getLogin(email, pass)
        }
    }

    suspend fun signupFunction(
        fullName: String,
        email: String,
        pass: String
    ): AuthResponse {
        return apiResponse { api.getSignup(fullName, email, pass) }
    }

    suspend fun saveUser(user: Users) = db.userDao().upsert(user)

    fun getUser() = db.userDao().getUser()
}