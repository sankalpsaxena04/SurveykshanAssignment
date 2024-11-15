package com.example.surveykshanassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.AudioRecordHelper
import com.example.surveykshanassignment.MainActivity
import com.example.surveykshanassignment.MainViewModel
import com.example.surveykshanassignment.PrefManager
import com.example.surveykshanassignment.R
import com.example.surveykshanassignment.dataItem
import com.example.surveykshanassignment.databinding.FragmentSubmitBinding

class SubmitFragment : Fragment() {
    private var _binding: FragmentSubmitBinding? = null
    private val binding get() = _binding!!
    private val viewModel:MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubmitBinding.inflate(inflater, container, false)
        binding.btnPrevious.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.isRecording.observe(viewLifecycleOwner) { isRecording ->
            if (!isRecording) {
                Toast.makeText(context, "Recording saved at: ${viewModel.audioFilePath.value}", Toast.LENGTH_LONG).show()
                val dataItem = dataItem(
                    viewModel.gender.value ?: "",
                    viewModel.age.value ?: 0,
                    viewModel.selfie.value ?: "",
                    viewModel.audioFilePath.value ?: "",
                    System.currentTimeMillis().toString()
                )
                PrefManager.saveData(dataItem)
                findNavController().navigate(R.id.action_submitFragment_to_summaryFragment)
            }
        }
        binding.submit.setOnClickListener {
            viewModel.stopRecording()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}