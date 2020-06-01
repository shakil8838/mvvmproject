package xyz.xandsoft.mvvmproject.model.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.xandsoft.mvvmproject.repositories.AuthenticationRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: AuthenticationRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(repository) as T
    }
}