package com.example.rickandmorty.presentation.characters.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.data.characters.CharacterDetailsRepository
import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmorty.domain.character.Character
import com.example.rickandmorty.presentation.characters.CharacterDetailsListener
import com.example.rickandmorty.presentation.characters.mapper.CharacterDetailsDataToCharacterMapper

class CharacterDetailsFragment : Fragment(R.layout.fragment_character_details), CharacterDetailsListener {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private val characterDetailsRepository: CharacterDetailsRepository by lazy {
        CharacterDetailsRepository()
    }
    private val mapper: CharacterDetailsDataToCharacterMapper by lazy {
        CharacterDetailsDataToCharacterMapper()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCharacterDetail()
    }

    override fun getCharacterById(character: CharactersData.CharacterDetailsData) {
        val characterDetail = mapper.map(character)
        with(binding) {
            nameCharacterDetails.text = characterDetail.name
            speciesCharacterDetails.text = characterDetail.species
            genderCharacterDetails.text = characterDetail.gender
            statusCharacterDetails.text = characterDetail.status
        }
        Glide.with(this@CharacterDetailsFragment)
            .load(characterDetail.image)
            .into(binding.imageCharacterDetails)
    }

    private fun initCharacterDetail() {
        val id = requireArguments().getInt(CHARACTER_ID)
        with(characterDetailsRepository) {
            registerListener(this@CharacterDetailsFragment)
            loadCharacterById(id)
        }
    }

    companion object {
        const val TAG = "CharacterDetailsFragment"
        private const val CHARACTER_ID = "ID"

        fun newInstance(character: Character) = CharacterDetailsFragment().also {
            it.arguments = bundleOf(
                CHARACTER_ID to character.id,
            )
        }
    }
}