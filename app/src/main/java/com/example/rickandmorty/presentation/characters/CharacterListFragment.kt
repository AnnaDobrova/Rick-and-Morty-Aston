package com.example.rickandmorty.presentation.characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.data.characters.CharactersRepository
import com.example.rickandmorty.data.characters.model.CharactersData
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.domain.character.CharactersDetailsListener
import com.example.rickandmorty.presentation.characters.adapter.CharacterAdapter
import com.example.rickandmorty.presentation.characters.mapper.CharacterDataToCharacterMapper

class CharacterListFragment : Fragment(R.layout.fragment_characters), CharactersListener {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter(requireActivity() as CharactersDetailsListener)
    }
    private val charactersRepository: CharactersRepository by lazy {
        CharactersRepository()
    }
    private val mapper: CharacterDataToCharacterMapper by lazy {
        CharacterDataToCharacterMapper()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAllCharacters()
        initRecycler()
    }

    /**
     * При реализации метода getAllCharacters, который является методом интерфейса CharactersListener
     * полученный список данных, которые придут из charactersRepository, мы отправляем в charactersAdapter
     * для отображения или обновления списка
     */
    override fun getAllCharacters(characters: List<CharactersData.CharacterDetailsData>) {
        characterAdapter.updateListCharacters(mapper.map(characters))
    }

    // 1 шаг регистрация листенера(колбэка) и требование начать загрузку всех персонажей
    private fun initAllCharacters() {
        with(charactersRepository) {
            registerListener(this@CharacterListFragment)
            loadAllCharacters()
        }
    }

    private fun initRecycler() {
        with(binding.recyclerViewCharacters) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = characterAdapter
        }
    }

    companion object {
        const val TAG_CHARACTERS_FRAGMENT = "TAG_CHARACTERS_FRAGMENT"


        fun newInstance() = CharacterListFragment()

    }

}