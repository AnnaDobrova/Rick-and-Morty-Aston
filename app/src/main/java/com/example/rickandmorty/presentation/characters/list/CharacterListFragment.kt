package com.example.rickandmorty.presentation.characters.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.characters.list.adapter.CharactersAdapter
import com.example.rickandmorty.presentation.characters.list.model.SingleCharacterUi
import com.example.rickandmorty.utils.ViewModelFactory
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

class CharacterListFragment : Fragment(R.layout.fragment_characters) {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(requireActivity() as CharacterListDetailsListener)
    }

    private var rickAndMortyComponent: RickAndMortyComponent? = null

    @Inject
    lateinit var viewModel: CharactersViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        rickAndMortyComponent = (activity as MainActivity).rickAndMortyComponent
        rickAndMortyComponent?.inject(this)
        callBackForFragments = requireActivity() as CallBackForFragments
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = rickAndMortyComponent!!.getViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[CharactersViewModel::class.java]

        binding.charactersPb.showProgress()
        observeVM()
        initRecycler()
        updateNetwork()
        searchCharacters()
        filter()
    }

    private fun observeVM() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllCharacters().collect { viewState ->
                    updateCharactersState(viewState)
                }
            }
        }
    }

    private fun updateCharactersState(state: ViewState<List<SingleCharacterUi>>) {
        when (state) {
            is ViewState.Loading -> updateLoadingState()
            is ViewState.Error -> updateErrorState(state.error.message.orEmpty())
            is ViewState.Data -> updateDataState(state.data)
        }

    }

    private fun updateDataState(list: List<SingleCharacterUi>) {
        charactersAdapter.updateListCharacters(list)
        binding.charactersPb.hideProgress()
    }

    private fun updateErrorState(message: String) {
        binding.charactersPb.hideProgress()
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateLoadingState() {
        binding.charactersPb.showProgress()
    }

    private fun initRecycler() {
        with(binding.recyclerViewCharacters) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = charactersAdapter
        }
    }

    private fun updateNetwork() {
        with(binding.swipeCharacters) {
            setOnRefreshListener {
                viewModel.loadCharacters()
                this.isRefreshing = false
            }
        }
    }

    private fun searchCharacters() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val list = mutableListOf<SingleCharacterUi>()
            val filterList = mutableListOf<SingleCharacterUi>()
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getAllCharacters().collect { viewState ->
                        when (viewState) {
                            is ViewState.Loading -> updateLoadingState()
                            is ViewState.Data -> list.addAll(viewState.data)
                            is ViewState.Error -> viewState.error
                        }
                    }
                }

            }
            for (i in list) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filterList.add(i)
                }
            }
            if (filterList.isNotEmpty()) {
                charactersAdapter.updateListCharacters(filterList)
            }
        }
    }

    private fun filter() {
        binding.filterIcon.setOnClickListener {
            val dialog = DialogFragmentCharacters()
            dialog.show(parentFragmentManager, DialogFragmentCharacters.TAG)
        }
    }

    companion object {
        const val TAG_CHARACTERS_FRAGMENT = "TAG_CHARACTERS_FRAGMENT"

        fun newInstance() = CharacterListFragment()
    }
}