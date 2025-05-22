package com.neu.mobileapplicationdevelopment202430

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import kotlin.properties.Delegates

class MVP : Fragment() {
    private lateinit var selectedItemSpinner1 : String
    private lateinit var selectedItemSpinner2 : String
    private var value by Delegates.notNull<Double>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_m_v_p, container, false)

        val spinner: Spinner = view.findViewById(R.id.spinner5)

        val spinner2: Spinner = view.findViewById(R.id.spinner6)

        val editTextNumber2: EditText = view.findViewById(R.id.editTextNumber2)
        val btn: Button = view.findViewById(R.id.button7)
        val result: TextView = view.findViewById(R.id.editTextText2)

        val listOfUnits = listOf("Kg", "Pound", "Litre", "Gallon", "Yard", "Meter", "Feet", "Cups")
        val arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listOfUnits)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        val arrayAdapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listOfUnits)
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = arrayAdapter2

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(x: AdapterView<*>?, v: View?, i: Int, l: Long) {
                selectedItemSpinner1 = listOfUnits[i]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(x: AdapterView<*>?, v: View?, i: Int, l: Long) {
                selectedItemSpinner2 = listOfUnits[i]
            }

            override fun onNothingSelected(x: AdapterView<*>?) {
            }
        }

        btn.setOnClickListener() {
            try {
                value = editTextNumber2.text.toString().toDouble()

            } catch (e: NumberFormatException) {
                Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            result.setText("" + convertingFunction(value, selectedItemSpinner1, selectedItemSpinner2))
        }







        return view
    }
    fun convertingFunction(value: Double, selectedItemSpinner1: String, selectedItemSpinner2: String): String {
        val massUnits = setOf("Kg", "Pound")
        val volumeUnits = setOf("Litre", "Gallon", "Cups")
        val lengthUnits = setOf("Yard", "Meter", "Feet")

        fun isCompatible(from: String, to: String): Boolean {
            return (from in massUnits && to in massUnits) ||
                    (from in volumeUnits && to in volumeUnits) ||
                    (from in lengthUnits && to in lengthUnits)
        }

        if (!isCompatible(selectedItemSpinner1, selectedItemSpinner2)) {
            Toast.makeText(requireContext(), "Invalid conversion types", Toast.LENGTH_SHORT).show()
            return ""
        }

        val convertingMap = mapOf<Pair<String, String>, Double>(
            "Kg" to "Pound" to 2.20462,
            "Pound" to "Kg" to 1 / 2.20462,
            "Litre" to "Gallon" to 0.264172,
            "Gallon" to "Litre" to 1 / 0.264172,
            "Litre" to "Cups" to 4.22675,
            "Cups" to "Litre" to 1 / 4.22675,
            "Gallon" to "Cups" to 16.0,
            "Cups" to "Gallon" to 1 / 16.0,
            "Yard" to "Meter" to 0.9144,
            "Meter" to "Yard" to 1 / 0.9144,
            "Feet" to "Meter" to 0.3048,
            "Meter" to "Feet" to 1 / 0.3048,
            "Yard" to "Feet" to 3.0,
            "Feet" to "Yard" to 1 / 3.0
        )



        if (selectedItemSpinner1 == selectedItemSpinner2) {
            return value.toString()
        }

        val key = Pair(selectedItemSpinner1, selectedItemSpinner2)
        val factor = convertingMap[key]

        return if (factor != null) {
            val result = value * factor
            result.toString()
        } else {
            Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_SHORT).show()
            "Invalid Input"
        }

    }



}