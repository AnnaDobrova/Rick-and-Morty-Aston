package com.example.rickandmorty.presentation.locations.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickandmorty.CallBackForFragments
import com.example.rickandmorty.MainActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentLocetionDetailsBinding
import com.example.rickandmorty.di.RickAndMortyComponent
import com.example.rickandmorty.presentation.characters.CharacterListDetailsListener
import com.example.rickandmorty.presentation.episodes.details.model.EpisodeDetailUi
import com.example.rickandmorty.presentation.locations.detail.adapter.CharacterListInLocationAdapter
import com.example.rickandmorty.presentation.locations.detail.model.LocationDetailUi
import com.example.rickandmorty.utils.ViewModelFactory
import com.example.rickandmorty.utils.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailsFragment : Fragment(R.layout.fragment_locetion_details) {

    private var _binding: FragmentLocetionDetailsBinding? = null
    private val binding get() = _binding!!
    private var callBackForFragments: CallBackForFragments? = null

    private val characterListInLocationAdapter: CharacterListInLocationAdapter by lazy {
        CharacterListInLocationAdapter(requireActivity() as CharacterListDetailsListener)
    }

    private var rickAndMortyComponent: RickAndMortyComponent? = null

    @Inject
    lateinit var viewModel: LocationDetailViewModel

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
        _binding = FragmentLocetionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val locationId = requireArguments().getInt(LOCATION_ID)

        viewModel = ViewModelProvider(this, viewModelFactory)[LocationDetailViewModel::class.java]

        viewModel.loadLocationDetail(locationId)
        observeVm()
        initRecycler()
        visibilityToolBar()
    }

    private fun observeVm() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getLocation().collect { viewState ->
                    updateCharactersDetailsState(viewState)
                }
            }
        }
    }

    private fun updateCharactersDetailsState(state: ViewState<LocationDetailUi>) {
        when (state) {
            is ViewState.Loading -> updateLoadingState()
            is ViewState.Error -> updateErrorState(state.error.message.orEmpty())
            is ViewState.Data -> updateDataState(state.data)
        }
    }

    private fun updateDataState(locationDetail: LocationDetailUi) {
        fillLocationDetail(locationDetail)
    }

    private fun updateErrorState(message: String) {
        binding.progressBar.hideProgress()
        Toast.makeText(
            requireActivity(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateLoadingState() {
        binding.progressBar.showProgress()
    }

    private fun fillLocationDetail(locationDetail: LocationDetailUi) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(binding) {
                    nameLocationDetails.text = locationDetail.name
                    typeLocationDetails.text = locationDetail.type
                    dimensionLocationDetails.text = locationDetail.dimension
                }
                viewModel.loadCharacterById(locationDetail.listCharactersInLocation)
                binding.progressBar.showProgress()
                viewModel.getCharacterList().collect { viewState ->
                    when (viewState) {
                        is ViewState.Loading -> updateLoadingState()
                        is ViewState.Error -> updateErrorState(viewState.error.message.orEmpty())
                        is ViewState.Data -> {
                            characterListInLocationAdapter.updateCharacterList(viewState.data)
                            binding.progressBar.hideProgress()
                        }
                    }
                }
            }
        }
    }

    private fun initRecycler() {
        Log.d("TAG", "initRecycler in EpisodeDetailsFragment ")
        with(binding.recyclerViewLocations) {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = characterListInLocationAdapter
        }
    }

    private fun visibilityToolBar() {
        binding.toolbarLocationDetail.setOnClickListener {
            callBackForFragments?.back()
            binding.toolbarLocationDetail.visibility = View.GONE
        }
    }

    companion object {
        const val TAG = "LocationDetailsFragment"
        private const val LOCATION_ID = "ID"

        fun newInstance(id: Int) = LocationDetailsFragment().also {
            it.arguments = bundleOf(
                LOCATION_ID to id
            )
        }
    }
}