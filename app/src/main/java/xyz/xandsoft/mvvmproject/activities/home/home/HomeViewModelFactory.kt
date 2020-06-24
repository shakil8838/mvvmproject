package xyz.xandsoft.mvvmproject.activities.home.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.xandsoft.mvvmproject.repositories.AuthenticationRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(
    private val authenticationRepository: AuthenticationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(authenticationRepository) as T
    }
}