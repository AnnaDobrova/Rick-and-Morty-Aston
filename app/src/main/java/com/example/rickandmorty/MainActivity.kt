package com.example.rickandmorty

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.FragmentActivity
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.domain.episode.list.model.Episode
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.domain.location.list.Location
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment.Companion.TAG_CHARACTERS_FRAGMENT
import com.example.rickandmorty.presentation.characters.detail.CharacterDetailsFragment
import com.example.rickandmorty.presentation.episodes.details.EpisodeDetailsFragment
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFragment
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFragment.Companion.TAG_EPISODES_FRAGMENT
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI
import com.example.rickandmorty.presentation.locations.LocationsFragment
import com.example.rickandmorty.presentation.locations.LocationsFragment.Companion.TAG_LOCATIONS_FRAGMENT
import com.example.rickandmorty.presentation.locations.details.LocationDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : FragmentActivity(R.layout.activity_main),
    CallBackForFragments,
    CharacterListDetailsListener,
    LocationListDetailsListener,
    EpisodeListDetailsListener {

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
            val characterListFragment = CharacterListFragment.newInstance()
            replace(R.id.fragment_container, characterListFragment, TAG_CHARACTERS_FRAGMENT)
            addToBackStack(TAG_CHARACTERS_FRAGMENT)
            commit()
        }
        bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_characters -> {
                    bottomNavigation?.visibility = VISIBLE

                    supportFragmentManager.beginTransaction().run {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container, CharacterListFragment.newInstance(), TAG_CHARACTERS_FRAGMENT)
                        addToBackStack(TAG_CHARACTERS_FRAGMENT)
                        commit()
                    }
                    true
                }
                R.id.nav_location -> {
                    bottomNavigation?.visibility = VISIBLE

                    supportFragmentManager.beginTransaction().run {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container, LocationsFragment.newInstance(), TAG_LOCATIONS_FRAGMENT)
                        addToBackStack(TAG_LOCATIONS_FRAGMENT)
                        commit()
                    }
                    true
                }
                R.id.nav_episodes -> {
                    bottomNavigation?.visibility = VISIBLE

                    supportFragmentManager.beginTransaction().run {
                        setReorderingAllowed(true)
                        replace(R.id.fragment_container, EpisodeListFragment.newInstance(), TAG_EPISODES_FRAGMENT)
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

    override fun goToDetailsCharacter(singleCharacterUi: SingleCharacterUi) {
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                CharacterDetailsFragment.newInstance(singleCharacterUi),
                CharacterDetailsFragment.TAG
            )
            addToBackStack(CharacterDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    override fun goToDetailsLocation(location: Location) {
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                LocationDetailsFragment.newInstance(location),
                LocationDetailsFragment.TAG
            )
            addToBackStack(LocationDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    override fun goToDetailsEpisode(episode: SingleEpisodeUI) {
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                EpisodeDetailsFragment.newInstance(episode),
                EpisodeDetailsFragment.TAG
            )
            addToBackStack(EpisodeDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    override fun back() {
        onBackPressed()
    }
}