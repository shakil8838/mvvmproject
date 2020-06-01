package xyz.xandsoft.mvvmproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import xyz.xandsoft.mvvmproject.R
import xyz.xandsoft.mvvmproject.activities.home.HomeActivity
import xyz.xandsoft.mvvmproject.data.db.AppDatabase
import xyz.xandsoft.mvvmproject.data.db.Users
import xyz.xandsoft.mvvmproject.databinding.ActivityLoginBinding
import xyz.xandsoft.mvvmproject.interfaces.authentication.AuthStateListener
import xyz.xandsoft.mvvmproject.interfaces.network.NetworkCall
import xyz.xandsoft.mvvmproject.model.viewmodel.AuthViewModelFactory
import xyz.xandsoft.mvvmproject.model.viewmodel.AuthenticationViewModel
import xyz.xandsoft.mvvmproject.repositories.AuthenticationRepository
import xyz.xandsoft.mvvmproject.utills.hide
import xyz.xandsoft.mvvmproject.utills.show
import xyz.xandsoft.mvvmproject.utills.showToast

class LoginActivity : AppCompatActivity(),
    AuthStateListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        // bind with UI
        bindTheUI()
    }

    private fun bindTheUI() {

        val userDatabase = AppDatabase(this)
        val authRepository = AuthenticationRepository(NetworkCall(), userDatabase)
        val authViewModelFactory = AuthViewModelFactory(authRepository)

        // Bind the UI
        val authBinding: ActivityLoginBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_login)

        val authDataModel = ViewModelProviders.of(this, authViewModelFactory)
            .get(AuthenticationViewModel::class.java)

        authBinding.authViewModel = authDataModel

        // bind with Interface
        authDataModel.mAuthListener = this

        authDataModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also { myIntent ->
                    myIntent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(myIntent)
                }
            }
        })
    }

    override fun onAuthenticationStarted() {
        progress_bar.show()
    }

    override fun onAuthenticationSuccess(users: Users) {
        progress_bar.hide()
    }

    override fun onAuthenticationFailed(message: String) {
        progress_bar.hide()
        showToast(message)
    }
}
