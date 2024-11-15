package com.example.surveykshanassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.AudioRecordHelper
import com.example.surveykshanassignment.ExtentionUtils.setOnClickThrottleBounceListener
import com.example.surveykshanassignment.MainActivity
import com.example.surveykshanassignment.MainViewModel
import com.example.surveykshanassignment.R
import com.example.surveykshanassignment.databinding.FragmentGenderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenderFragment : Fragment() {
    private var _binding : FragmentGenderBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenderBinding.inflate(inflater, container, false)
        binding.btnNext.setOnClickListener {
            if(viewModel.gender.value == null|| viewModel.gender.value == "Select Gender"){
                Toast.makeText(requireContext(), "Please select your gender", Toast.LENGTH_SHORT).show()
            }
            else{
                findNavController().navigate(R.id.action_genderFragment_to_ageFragment)
            }
        }
        viewModel.startRecording()

        val genderTypes = arrayOf(
            "Male", "Female", "Other"
        )


        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            genderTypes
        ).also { it.setDropDownViewResource(R.layout.spinner_item) }

        binding.dropdown.adapter = adapter

        binding.dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.setGender(genderTypes[position])

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.setGender(null)
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}