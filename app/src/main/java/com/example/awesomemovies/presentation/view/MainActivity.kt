package com.barriosartola.awesomeapp.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barriosartola.awesomeapp.data.controller.RetrofitController
import com.barriosartola.awesomeapp.presentation.view.home.HomeActivity
import com.example.awesomemovies.data.repository.genres.CloudGenreDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val retrofitController: RetrofitController by inject()
    private val cloudGenreDataStore: CloudGenreDataStore by inject()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadGenres()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loadGenres() {
        launch(Dispatchers.IO) {
            try {cloudGenreDataStore.getGenres()}
            catch (error: Exception){}
            finally {}
         }
    }
}
