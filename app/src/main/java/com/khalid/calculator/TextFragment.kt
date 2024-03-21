// TextFragment.kt
package com.khalid.calculator

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khalid.calculator.databinding.FragmentTextBinding

class TextFragment : Fragment() {

    public lateinit var binding : FragmentTextBinding
    private var digitAppended = false
    private var operator: Char? = null
    private var firstNumber: String? = null
    private var secondNumber: String? = null
    private var decimalClicked = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun appendText(fontSize: Int, text: String) {
        if (!digitAppended) {
            // If no digit has been appended yet, replace the initial "0" with the new digit
            changeTextProperties(fontSize, text)
            digitAppended = true
        } else {
            // Otherwise, append the new digit to the existing text
            val currentText = binding.calcText.text.toString()
            val newText = currentText + text
            changeTextProperties(fontSize, newText)
        }
    }

    fun changeTextProperties(fontsize: Int, text: String) {
        binding.calcText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize.toFloat())
        binding.calcText.setText(text)
    }

    fun resetDigitAppended() {
        digitAppended = false
    }

    //Setter Methods
    fun setOperator(op: Char) {
        operator = op
    }

    fun setFirstNumber(number1: String) {
        firstNumber = number1
    }

    fun setSecondNumber(number2: String) {
        secondNumber = number2
    }

    fun setDecimalClicked(clicked: Boolean) {
        decimalClicked = clicked
    }

    //Getter Methods
    fun getOperator() : Char?{
        return operator
    }
    fun getFirstNumber() : Float? {
        return firstNumber?.toFloatOrNull() ?: 0f
    }

    fun getSecondNumber() : Float?{
        return secondNumber?.substring(1)?.toFloatOrNull() ?: 2f
    }

    fun isDecimalClicked(): Boolean {
        return decimalClicked
    }

    fun resetDecimal() {
        decimalClicked = false
    }




}
