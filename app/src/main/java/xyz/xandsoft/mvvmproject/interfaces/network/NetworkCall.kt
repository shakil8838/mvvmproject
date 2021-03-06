package xyz.xandsoft.mvvmproject.interfaces.network

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import xyz.xandsoft.mvvmproject.data.response.AuthResponse

interface NetworkCall {

    @FormUrlEncoded
    @POST("login")
    suspend fun getLogin(
        @Field("email") loginEmail: String,
        @Field("password") loginPassword: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("singup")
    suspend fun getSignup(
        @Field("name") fullName: String?,
        @Field("email") singupEmail: String?,
        @Field("password") signupPassword: String?
    ): Response<AuthResponse>

    companion object {
        operator fun invoke(
            networkInterceptor: NetworkInterceptor
        ): NetworkCall {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor).build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkCall::class.java)
        }
    }
}