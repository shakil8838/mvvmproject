package xyz.xandsoft.mvvmproject.data.response

import org.json.JSONObject
import retrofit2.Response
import xyz.xandsoft.mvvmproject.utills.Exception

abstract class ApiResponse {

    suspend fun <T : Any> apiResponse(call: suspend () -> Response<T>): T {

        val theResponse = call.invoke()

        if (theResponse.isSuccessful) {
            return theResponse.body()!! // If the response is successful simply return the response
        } else {

            val theErrorResponse = theResponse.errorBody()?.string()    // get the error response
            val messageResponse = StringBuilder()

            theErrorResponse?.let {

                try {
                    messageResponse.append(JSONObject(it).getString("message"))
                } catch (e: java.lang.Exception) {
                }

                messageResponse.append("\n")
            }

            // Finishing the string building
            messageResponse.append("Error code: ${theResponse.code()}")

            // Finally throw the api exception
            throw Exception(messageResponse.toString())
        }
    }
}