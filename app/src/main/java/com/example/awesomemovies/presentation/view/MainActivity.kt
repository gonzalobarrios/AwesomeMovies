package com.barriosartola.awesomeapp.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barriosartola.awesomeapp.data.controller.RetrofitController
import com.barriosartola.awesomeapp.presentation.view.auth.AuthActivity
import com.barriosartola.awesomeapp.presentation.view.home.HomeActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val retrofitController: RetrofitController by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
