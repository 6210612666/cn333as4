package com.example.unitconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unitconverter.R

class MassViewModel : ViewModel() {
    private val _weight: MutableLiveData<Int> = MutableLiveData(R.string.kilogram)

    val weight: LiveData<Int>
        get() = _weight

    fun setWeight(value: Int) {
        _weight.value = value
    }

    private val _mass: MutableLiveData<String> = MutableLiveData("")

    val mass: LiveData<String>
        get() = _mass

    fun getMassAsFloat(): Float = (_mass.value ?: "").let {
        return try {
            it.toFloat()
        } catch (e: NumberFormatException) {
            Float.NaN
        }
    }

    fun setMass(value: String) {
        _mass.value = value
    }

    fun convert() = getMassAsFloat().let {
        if (!it.isNaN())
            if (_weight.value == R.string.ton)
                it * 1000
            else
                it / 1000
        else
            Float.NaN
    }
}