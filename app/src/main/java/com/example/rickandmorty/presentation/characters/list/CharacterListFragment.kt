package com.example.rickandmorty.presentation.characters.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
import java.util.Locale
import javax.inject.Inject

class CharacterListFragment : Fragment(R.layout.fragment_characters) {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val charactersAdapter: CharactersAdapter by lazy {
        CharactersAdapter(requireActivity() as CharacterListDetailsListener)
    }
    var rickAndMortyComponent: RickAndMortyComponent? = null

    @Inject lateinit var viewModel: CharactersViewModel
    @Inject lateinit var viewModelFactory: ViewModelFactory

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
        isConnect()
        observeVM()
        initRecycler()
        updateNetwork()
        searchCharacters()
        filter()
    }

    // 2-13
    // 2 шаг подписываемся на то что бы слушать данные из getAllCharacters,
    // 13 шаг как только они изменяться  то мы их сразу применим
    private fun observeVM() {
        viewModel.getAllCharacters().observe(viewLifecycleOwner) { newCharacterList ->
            charactersAdapter.updateListCharacters(newCharacterList)
            binding.charactersPb.hideProgress()
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

    private fun updateNetwork() {
        with(binding.swipeCharacters) {
            setOnRefreshListener {
                viewModel.loadAllCharacters()
                this.isRefreshing = false
            }
        }
    }

    private fun isConnect(): Boolean {
        val connectivityManager = (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        connectivityManager.apply {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getNetworkCapabilities(activeNetwork)?.run {
                    when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                } ?: false
            } else {
                activeNetworkInfo?.isConnected ?: false
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
            viewModel.getAllCharacters().observe(viewLifecycleOwner) { newCharacterList ->
                list.addAll(newCharacterList)
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