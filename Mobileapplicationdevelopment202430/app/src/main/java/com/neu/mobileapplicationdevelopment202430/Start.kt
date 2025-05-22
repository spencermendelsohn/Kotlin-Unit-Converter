package com.neu.mobileapplicationdevelopment202430

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.neu.mobileapplicationdevelopment202430.databinding.FragmentStartBinding

class Start : Fragment() {


    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.goToMVP.setOnClickListener {
            it.findNavController().navigate(R.id.action_start2_to_MVP)
        }

        binding.goToMVVM.setOnClickListener {
            it.findNavController().navigate(R.id.action_start2_to_MVVM)
        }

        return binding.root
    }
}