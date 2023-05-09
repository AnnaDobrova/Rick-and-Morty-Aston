package com.example.rickandmorty.presentation.characters.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentCharactersBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.characters.list.adapter.CharactersAdapter
import com.example.rickandmorty.utils.ViewModelFactory
import com.example.rickandmorty.utils.network.NetworkState
import javax.inject.Inject

class CharacterListFragment : Fragment(R.layout.fragment_characters) {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(requireActivity() as CharacterListDetailsListener)
    }
    var rickAndMortyComponent: RickAndMortyComponent? = null

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
        lifecycleScope.launchWhenStarted {
            viewModel.getAllCharacters()?.collect { newCharacterList ->
                charactersAdapter.submitData(newCharacterList)
                binding.charactersPb.hideProgress()
            }
            viewModel.getNetworkState().collect { state ->
                if (state is NetworkState.Unavailable) {
                    binding.charactersPb.hideProgress()
                    Toast.makeText(requireContext(), R.string.error_connectivity, Toast.LENGTH_SHORT).show()
                }
            }
        }
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
                viewModel.initAllCharacters()
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
        lifecycleScope.launchWhenStarted {
            if (query != null) {
                viewModel.getAllCharacters()?.collect { newCharacterList ->
                    charactersAdapter.submitData(newCharacterList)
                    binding.charactersPb.hideProgress()
                }
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