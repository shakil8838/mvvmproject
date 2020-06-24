package xyz.xandsoft.mvvmproject.activities.home.home

import androidx.lifecycle.ViewModel
import xyz.xandsoft.mvvmproject.repositories.AuthenticationRepository

class HomeViewModel(
    repository: AuthenticationRepository
) : ViewModel() {

    val users = repository.getUser()
}