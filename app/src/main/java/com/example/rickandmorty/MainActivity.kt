package com.example.rickandmorty

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.characters.CharactersFragment
import com.example.rickandmorty.presentation.characters.CharactersFragment.Companion.TAG_CHARACTERS_FRAGMENT
import com.example.rickandmorty.presentation.episodes.EpisodesFragment
import com.example.rickandmorty.presentation.episodes.EpisodesFragment.Companion.TAG_EPISODES_FRAGMENT
import com.example.rickandmorty.presentation.locations.LocationsFragment
import com.example.rickandmorty.presentation.locations.LocationsFragment.Companion.TAG_LOCATIONS_FRAGMENT
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : FragmentActivity(R.layout.activity_main), CallBackForFragments {

    private var bottomNavigation: BottomNavigationView? = null
    private lateinit var binder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        navListener()

    }

    private fun navListener() {
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            val charactersFragment = CharactersFragment.newInstance()
            replace(R.id.fragment_container, charactersFragment, TAG_CHARACTERS_FRAGMENT)
            addToBackStack(TAG_CHARACTERS_FRAGMENT)
            commit()
        }
        bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_characters -> {
                    supportFragmentManager.beginTransaction().run {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container, CharactersFragment.newInstance(), TAG_CHARACTERS_FRAGMENT)
                        addToBackStack(TAG_CHARACTERS_FRAGMENT)
                        commit()
                    }
                    true
                }
                R.id.nav_location -> {
                    supportFragmentManager.beginTransaction().run {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container, LocationsFragment.newInstance(), TAG_LOCATIONS_FRAGMENT)
                        addToBackStack(TAG_LOCATIONS_FRAGMENT)
                        commit()
                    }
                    true
                }
                R.id.nav_episodes -> {
                    supportFragmentManager.beginTransaction().run {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container, EpisodesFragment.newInstance(), TAG_EPISODES_FRAGMENT)
                        addToBackStack(TAG_EPISODES_FRAGMENT)
                        commit()
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }

    }

    override fun back() {
        onBackPressed()
    }
}