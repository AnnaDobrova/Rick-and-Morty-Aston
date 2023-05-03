package com.example.rickandmorty.presentation.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.DialogFragmentCharactersBinding

class DialogFragmentCharacters() : DialogFragment() {

    private var _binding: DialogFragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private var filter: CharacterListFragment? = null

    @Deprecated("Deprecated in Java")
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        filter = requireParentFragment() as CharacterListFragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogFragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        openFilters()
        binding.ButtonCansel.setOnClickListener {
            dismiss()
        }
        binding.buttonSave.setOnClickListener {
       filter?.filterStatus(filterStatus())
       filter?.filterSpecies(filterSpecies())
       filter?.filterGender(filterGender())
        }
    }

    private fun openFilterStatus() {
        binding.arrowDownStatus.setOnClickListener {
            binding.arrowDownStatus.visibility = INVISIBLE
            binding.arrowUpStatus.visibility = VISIBLE
            binding.radioGroupStatus.visibility = VISIBLE
        }
        binding.arrowUpStatus.setOnClickListener {
            binding.arrowDownStatus.visibility = VISIBLE
            binding.arrowUpStatus.visibility = INVISIBLE
            binding.radioGroupStatus.visibility = GONE
        }
    }

    private fun openFilterSpecies() {
        binding.arrowDownSpecies.setOnClickListener {
            binding.arrowDownSpecies.visibility = INVISIBLE
            binding.arrowUpSpecies.visibility = VISIBLE
            binding.radioGroupSpecies.visibility = VISIBLE
        }
        binding.arrowUpSpecies.setOnClickListener {
            binding.arrowDownSpecies.visibility = VISIBLE
            binding.arrowUpSpecies.visibility = INVISIBLE
            binding.radioGroupSpecies.visibility = GONE
        }
    }

    private fun openFilterGender() {
        binding.arrowDownGender.setOnClickListener {
            binding.arrowDownGender.visibility = INVISIBLE
            binding.arrowUpGender.visibility = VISIBLE
            binding.radioGroupGender.visibility = VISIBLE
        }
        binding.arrowUpGender.setOnClickListener {
            binding.arrowDownGender.visibility = VISIBLE
            binding.arrowUpGender.visibility = INVISIBLE
            binding.radioGroupGender.visibility = GONE
        }
    }

    private fun openFilters() {
        openFilterStatus()
        openFilterSpecies()
        openFilterGender()
    }

    private fun filterStatus():String {
        val selected =
            when (binding.radioGroupStatus.checkedRadioButtonId) {
                binding.statusAlive.id -> {
                    binding.statusAlive.text.toString()
                }

                binding.statusDead.id -> {
                    binding.statusDead.text.toString()
                }

                binding.statusUnknown.id -> {
                    binding.statusUnknown.text.toString()
                }

                else -> {
                    " "
                }
            }
        return selected
    }

    private fun filterSpecies(): String {
        val selected =
            when (binding.radioGroupSpecies.checkedRadioButtonId) {
                binding.speciesAlien.id -> {
                    binding.speciesAlien.text.toString()
                }

                binding.speciesHuman.id -> {
                    binding.speciesHuman.text.toString()
                }

                else -> {
                    " "
                }
            }
        return selected
    }

    private fun filterGender():String {
        val selected =
            when (binding.radioGroupGender.checkedRadioButtonId) {
                binding.genderMale.id -> {
                    binding.genderMale.text.toString()
                }

                binding.genderFemale.id -> {
                    binding.genderFemale.text.toString()
                }

                binding.genderGenderless.id -> {
                    binding.genderGenderless.text.toString()
                }

                binding.genderUnknown.id -> {
                    binding.genderUnknown.text.toString()
                }

                else -> {
                    " "
                }
            }
        return selected

    }

    companion object {
        const val TAG = "DialogFragment"
    }
}