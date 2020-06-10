package xyz.xandsoft.mvvmproject.model.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import xyz.xandsoft.mvvmproject.interfaces.authentication.AuthStateListener
import xyz.xandsoft.mvvmproject.repositories.AuthenticationRepository
import xyz.xandsoft.mvvmproject.utills.Exception
import xyz.xandsoft.mvvmproject.utills.Coroutines

class AuthenticationViewModel(
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    var fullName: String? = null
    var loginEmail: String? = null
    var loginPass: String? = null
    var confirmPassword: String? = null

    var mAuthListener: AuthStateListener? = null

    fun loginBtnClick(view: View) {

        if (loginEmail == null || loginPass == null) {
            mAuthListener?.onAuthenticationFailed("Please enter all the fields")
            return
        }

        mAuthListener?.onAuthenticationStarted()

        // Getting the login response
        Coroutines.main {

            try {

                val loginResponse = authRepository.loginFunction(loginEmail!!, loginPass!!)

                loginResponse.user?.let {
                    mAuthListener?.onAuthenticationSuccess(it)
                    // If User is not null just return to the thread
                    authRepository.saveUser(it)
                    return@main
                }

                // Else the User is null show the error message
                mAuthListener?.onAuthenticationFailed(loginResponse.message!!)
            } catch (e: Exception) {
                mAuthListener?.onAuthenticationFailed(e.toString())
            }
        }
    }

    fun getLoggedInUser() = authRepository.getUser()

    fun onSignupBtnClick(view: View) {

    }

    fun onSignupSubmitBtnClick(view: View) {

        if (loginEmail == null) {
            mAuthListener?.onAuthenticationFailed("Please enter all the fields")
            return
        }

        if (loginPass == null) {
            mAuthListener?.onAuthenticationFailed("Please enter all the fields")
            return
        }

        if (fullName == null) {
            mAuthListener?.onAuthenticationFailed("Please enter all the fields")
            return
        }

        if (!confirmPassword.equals(loginPass)) {
            mAuthListener?.onAuthenticationFailed("Confirm password must same as password")
            return
        }

        mAuthListener?.onAuthenticationStarted()

        // Getting the login response
        Coroutines.main {

            try {

                val loginResponse =
                    authRepository.signupFunction(fullName!!, loginEmail!!, loginPass!!)

                loginResponse.user?.let {
                    mAuthListener?.onAuthenticationSuccess(it)
                    // If User is not null just return to the thread
                    authRepository.saveUser(it)
                    return@main
                }

                // Else the User is null show the error message
                mAuthListener?.onAuthenticationFailed(loginResponse.message!!)
            } catch (e: Exception) {
                mAuthListener?.onAuthenticationFailed(e.toString())
            }
        }
    }
}