package com.example.rickandmorty

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.FragmentActivity
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.di.DaggerRickAndMortyComponent
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.domain.episode.EpisodeListDetailsListener
import com.example.rickandmorty.domain.location.LocationListDetailsListener
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment
import com.example.rickandmorty.presentation.characters.list.CharacterListFragment.Companion.TAG_CHARACTERS_FRAGMENT
import com.example.rickandmorty.presentation.characters.detail.CharacterDetailsFragment
import com.example.rickandmorty.presentation.characters.detail.model.CharacterDetailUi
import com.example.rickandmorty.presentation.episodes.details.EpisodeDetailsFragment
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
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

    var rickAndMortyComponent: RickAndMortyComponent = DaggerRickAndMortyComponent.factory().create(this)

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

    override fun goToDetailsCharacter(character: SingleCharacterUi) {
        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                CharacterDetailsFragment.newInstance(character.id),
                CharacterDetailsFragment.TAG
            )
            addToBackStack(CharacterDetailsFragment.TAG)
            commit()
        }
        bottomNavigation?.visibility = GONE
    }

    override fun goToDetailsCharacter(character: CharacterDetailUi) {

        supportFragmentManager.beginTransaction().run {
            setReorderingAllowed(true)
            replace(
                R.id.fragment_container,
                CharacterDetailsFragment.newInstance(character.id),
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

    override fun goToDetailsEpisode(episode: EpisodeDetailUi) {
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
        val index = episodeString.lastIndex
        val threeLastChar = "${episodeString[index - 2]}${episodeString[index - 1]}${episodeString.last()}"
        val secondLastChar = "${episodeString[index - 1]}${episodeString.last()}"
        val id: Int = if (
            isNumeric(threeLastChar)
        ) {
            threeLastChar.toInt()
        } else if (
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
        if (supportFragmentManager.findFragmentByTag(CharacterDetailsFragment.TAG) == null
            && supportFragmentManager.findFragmentByTag(EpisodeDetailsFragment.TAG) == null
            && supportFragmentManager.findFragmentByTag(LocationDetailsFragment.TAG) == null
        ) {
            binder.bottomNavigation.visibility = VISIBLE
        } else {
            binder.bottomNavigation.visibility = GONE
        }
    }
}