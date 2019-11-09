package com.barriosartola.awesomeapp.presentation.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.barriosartola.awesomeapp.R
import com.barriosartola.awesomeapp.presentation.view.home.movies.MoviesFragment
import com.barriosartola.awesomeapp.presentation.view.home.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        showFragment(MoviesFragment(), NotesFragmentTag)
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            removeActiveFragment()

            when (menuItem.itemId) {
                R.id.notes -> showFragment(MoviesFragment(), MoviesFragmentTag)
                R.id.profile -> showFragment(ProfileFragment(), ProfileFragmentTag)
            }

            true
        }
    }

    private fun removeActiveFragment() {
        listOf(MoviesFragmentTag, ProfileFragmentTag).forEach { tag ->
            val fragment = supportFragmentManager.findFragmentByTag(tag)
            fragment?.let {
                supportFragmentManager
                    .beginTransaction()
                    .remove(it)
                    .commit()
            }
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment, tag)
            .commit()
    }

    companion object {
        private const val NotesFragmentTag = "NotesFragmentTag"
        private const val ProfileFragmentTag = "ProfileFragmentTag"

        private const val MoviesFragmentTag = "MoviesFragmentTag"
    }
}
