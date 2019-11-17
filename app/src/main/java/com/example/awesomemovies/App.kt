package com.barriosartola.awesomeapp

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.barriosartola.awesomeapp.data.controller.RetrofitController
import com.barriosartola.awesomeapp.inject.*
import com.barriosartola.awesomeapp.presentation.view.accessTokenKey
import com.barriosartola.awesomeapp.presentation.view.auth.AuthActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class App : Application() {
    private val retrofitController: RetrofitController by inject()
    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(loginModule, networkModule, databaseModule, moviesModule, genreModule, movieGenreModule, reviewsModule))
        }

        // Initializing LocalDate backport
        AndroidThreeTen.init(this)

        listenActivityCallbacks()

    }

    private fun listenActivityCallbacks() {
        registerActivityLifecycleCallbacks(Lifecycle())
    }

    inner class Lifecycle : ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
            activity?.let {
                currentActivity = WeakReference(it)
            }
        }

        override fun onActivityStarted(activity: Activity?) {
            activity?.let {
                currentActivity = WeakReference(it)
            }
        }

        override fun onActivityDestroyed(activity: Activity?) {
            if (activity == currentActivity.get()) {
                currentActivity.clear()
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        }
    }

    companion object {
        var currentActivity = WeakReference<Activity>(null)

        fun goToLoginScreen() {
            currentActivity.get()?.let {
                val intent = Intent(it, AuthActivity::class.java)
                it.startActivity(intent)
                it.finish()
            }
        }
    }
}
