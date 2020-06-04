package xyz.xandsoft.mvvmproject

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import xyz.xandsoft.mvvmproject.data.db.AppDatabase
import xyz.xandsoft.mvvmproject.interfaces.network.NetworkCall
import xyz.xandsoft.mvvmproject.interfaces.network.NetworkInterceptor
import xyz.xandsoft.mvvmproject.model.viewmodel.AuthViewModelFactory
import xyz.xandsoft.mvvmproject.repositories.AuthenticationRepository

class BaseApplication : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))

        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { NetworkCall(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { AuthenticationRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}