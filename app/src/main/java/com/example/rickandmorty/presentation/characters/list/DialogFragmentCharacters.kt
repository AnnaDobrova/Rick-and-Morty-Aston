package com.example.rickandmorty.presentation.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rickandmorty.databinding.DialogFragmentCharactersBinding

class DialogFragmentCharacters : DialogFragment() {

    private var _binding: DialogFragmentCharactersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
            val selectedIdStatus = binding.radioGroupStatus.checkedRadioButtonId
            val selectedIdSpecies = binding.radioGroupSpecies.checkedRadioButtonId
            val selectedIdGender = binding.radioGroupGender.checkedRadioButtonId
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

    companion object {
        const val TAG = "DialogFragment"
    }
}