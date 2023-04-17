package com.example.rickandmorty.presentation.characters.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.characters.list.adapter.CharactersAdapter

class CharacterListFragment : Fragment(R.layout.fragment_characters) {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(requireActivity() as CharacterListDetailsListener)
    }

    private val viewModel: CharactersViewModel by lazy {
        CharactersViewModel()
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
        observeVM()
        initRecycler()
    }

    // 2-13
    // 2 шаг подписываемся на то что бы слушать данные из getAllCharacters,
    // 13 шаг как только они изменяться  то мы их сразу применим
    private fun observeVM() {
        viewModel.getAllCharacters().observe(viewLifecycleOwner) { newCharacterList ->
            charactersAdapter.updateListCharacters(newCharacterList)
        }
    }

//    /**
//     * При реализации метода getAllCharacters, который является методом интерфейса CharactersListener
//     * полученный список данных, которые придут из charactersRepository, мы отправляем в charactersAdapter
//     * для отображения или обновления списка
//     */

    // 1 шаг регистрация листенера(колбэка) и требование начать загрузку всех персонажей

    private fun initRecycler() {
        with(binding.recyclerViewCharacters) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = charactersAdapter
        }
    }

    companion object {
        const val TAG_CHARACTERS_FRAGMENT = "TAG_CHARACTERS_FRAGMENT"

        fun newInstance() = CharacterListFragment()

    }

}