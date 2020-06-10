package xyz.xandsoft.mvvmproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import xyz.xandsoft.mvvmproject.R
import xyz.xandsoft.mvvmproject.activities.home.HomeActivity
import xyz.xandsoft.mvvmproject.data.db.Users
import xyz.xandsoft.mvvmproject.databinding.ActivityLoginBinding
import xyz.xandsoft.mvvmproject.databinding.ActivitySignupBinding
import xyz.xandsoft.mvvmproject.interfaces.authentication.AuthStateListener
import xyz.xandsoft.mvvmproject.model.viewmodel.AuthViewModelFactory
import xyz.xandsoft.mvvmproject.model.viewmodel.AuthenticationViewModel
import xyz.xandsoft.mvvmproject.utills.hide
import xyz.xandsoft.mvvmproject.utills.show
import xyz.xandsoft.mvvmproject.utills.showToast

class SignupActivity : AppCompatActivity(), AuthStateListener, KodeinAware {

    override val kodein by kodein
    private val authViewModelFactory: AuthViewModelFactory by instance<AuthViewModelFactory>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authBinding: ActivitySignupBinding = DataBindingUtil
            .setContentView(this, R.layout.activity_signup)

        val authDataModel = ViewModelProviders.of(this, authViewModelFactory)
            .get(AuthenticationViewModel::class.java)

        authBinding.authViewModel = authDataModel

        // bind with Interface
        authDataModel.mAuthListener = this
        // End of Binging UI

        // Checking is user is currently logged in or not
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
