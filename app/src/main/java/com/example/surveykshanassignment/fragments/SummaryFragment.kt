package com.example.surveykshanassignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surveykshanassignment.DataAdapter
import com.example.surveykshanassignment.PrefManager
import com.example.surveykshanassignment.R
import com.example.surveykshanassignment.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        PrefManager.getData().also {
            val adapter = it?.let { it1 -> DataAdapter(requireContext(), it1) }
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = adapter
            return binding.root
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        (binding.recyclerView.adapter as? DataAdapter)?.releaseMediaPlayer()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}