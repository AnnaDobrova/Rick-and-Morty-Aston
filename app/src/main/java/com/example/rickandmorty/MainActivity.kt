package com.example.rickandmorty

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.FragmentActivity
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment.Companion.TAG_CHARACTERS_FRAGMENT
import com.example.rickandmorty.presentation.characters.detail.CharacterDetailsFragment
import com.example.rickandmorty.presentation.episodes.details.EpisodeDetailsFragment
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFragment
import com.example.rickandmorty.presentation.episodes.list.EpisodeListFragment.Companion.TAG_EPISODES_FRAGMENT
import com.example.rickandmorty.presentation.episodes.list.model.SingleEpisodeUI
import com.example.rickandmorty.presentation.locations.list.LocationListFragment
import com.example.rickandmorty.presentation.locations.list.LocationListFragment.Companion.TAG_LOCATIONS_FRAGMENT
import com.example.rickandmorty.presentation.locations.detail.LocationDetailsFragment
import com.example.rickandmorty.presentation.locations.list.model.SingleLocationUI
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
                        replace(R.id.fragment_container, LocationListFragment.newInstance(), TAG_LOCATIONS_FRAGMENT)
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
                CharacterDetailsFragment.newInstance(singleCharacterUi.id),
                CharacterDetailsFragment.TAG
            )
            addToBackStack(CharacterDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    //TODO delete
    override fun getCharacterString(characterString: String) {
        supportFragmentManager.beginTransaction().run {
            val index = characterString.lastIndex - 1
            val secondLastChar = "${characterString[index]}${characterString.last()}"
            val id: Int = if (
                isNumeric(secondLastChar)
            ) {
                secondLastChar.toInt()
            } else {
                characterString.last().digitToInt()
            }
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                CharacterDetailsFragment.newInstance(id),
                CharacterDetailsFragment.TAG
            )
            addToBackStack(CharacterDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    override fun goToDetailsLocation(location: SingleLocationUI) {
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                LocationDetailsFragment.newInstance(location.id.toInt()),
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
                EpisodeDetailsFragment.newInstance(episode.id),
                EpisodeDetailsFragment.TAG
            )
            addToBackStack(EpisodeDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    // TODO потом удалить
    override fun goToDetailsEpisodeString(episodeString: String) {
        val index = episodeString.lastIndex - 1
        val secondLastChar = "${episodeString[index]}${episodeString.last()}"
        val id: Int = if (
            isNumeric(secondLastChar)
        ) {
            secondLastChar.toInt()
        } else {
            episodeString.last().digitToInt()
        }
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                EpisodeDetailsFragment.newInstance(id),
                EpisodeDetailsFragment.TAG
            )
            addToBackStack(EpisodeDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    private fun isNumeric(s: String): Boolean {
        return s.chars().allMatch { Character.isDigit(it) }
    }

    override fun back() {
        onBackPressed()
    }
}