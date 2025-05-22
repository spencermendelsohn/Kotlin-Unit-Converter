package com.neu.mobileapplicationdevelopment202430

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MVVMViewModel : ViewModel() {
    private val r = MutableLiveData<String>()
    val result: LiveData<String> = r

    fun convert(value: String?, from: String, to: String) {
        val massUnits = setOf("Kg", "Pound")
        val volumeUnits = setOf("Litre", "Gallon", "Cups")
        val lengthUnits = setOf("Yard", "Meter", "Feet")

        fun isCompatible(from: String, to: String): Boolean {
            return (from in massUnits && to in massUnits) ||
                    (from in volumeUnits && to in volumeUnits) ||
                    (from in lengthUnits && to in lengthUnits)
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

        val num = value?.toDouble()
        if (num == null) {
            r.value = "Invalid input"
            return
        }

        if (from == to) {
            r.value = num.toString()
            return
        }

        if (!isCompatible(from, to)) {
            r.value = "Not correct units"
            return
        }

        val factor = convertingMap[from to to]
        if (factor != null) {
            r.value = (num * factor).toString()
        } else {
            r.value = "Not allowed to convert"
        }
    }
}
