package com.neu.mobileapplicationdevelopment202430

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

class MVVM : Fragment() {

    private val viewModel: MVVMViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_m_v_v_m, container, false)

        val spinner1: Spinner = view.findViewById(R.id.spinner5)
        val spinner2: Spinner = view.findViewById(R.id.spinner6)

        val edit: EditText = view.findViewById(R.id.editTextNumber2)
        val resultText: TextView = view.findViewById(R.id.editTextText2)

        val convertBtn: Button = view.findViewById(R.id.button7)

        val units = listOf("Kg", "Pound", "Litre", "Gallon", "Yard", "Meter", "Feet", "Cups")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        convertBtn.setOnClickListener {
            val input = edit.text.toString()
            val from = spinner1.selectedItem.toString()
            val to = spinner2.selectedItem.toString()
            viewModel.convert(input, from, to)
        }

        viewModel.result.observe(viewLifecycleOwner) {
            resultText.text = it
        }

        return view
    }
}
