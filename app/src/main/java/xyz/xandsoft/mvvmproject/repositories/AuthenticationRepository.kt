package xyz.xandsoft.mvvmproject.repositories

import retrofit2.Response
import xyz.xandsoft.mvvmproject.data.response.ApiResponse
import xyz.xandsoft.mvvmproject.data.response.AuthResponse
import xyz.xandsoft.mvvmproject.interfaces.network.NetworkCall

class AuthenticationRepository : ApiResponse() {

    suspend fun loginFunction(email: String, pass: String): AuthResponse {
        return apiResponse {
            NetworkCall().getLogin(email, pass)
        }
    }
}