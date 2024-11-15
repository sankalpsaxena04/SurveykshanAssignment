package com.example.surveykshanassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.surveykshanassignment.MainActivity
import com.example.surveykshanassignment.MainViewModel
import com.example.surveykshanassignment.R
import com.example.surveykshanassignment.databinding.FragmentAgeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgeFragment : Fragment() {

    private var _binding: FragmentAgeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgeBinding.inflate(inflater, container, false)
        binding.btnPrevious.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.age.observe(viewLifecycleOwner){
            binding.ageET.setText(it.toString())
        }

        binding.btnNext.setOnClickListener {
            if(binding.ageET.text.toString().isNotEmpty()){
                viewModel.setAge(binding.ageET.text.toString().toInt())
                findNavController().navigate(R.id.action_ageFragment_to_selfieFragment)
            }
            else{
                Toast.makeText(requireContext(), "Please enter your age", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}