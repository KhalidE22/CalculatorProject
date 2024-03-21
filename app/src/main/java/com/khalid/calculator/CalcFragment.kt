// CalcFragment.kt
package com.khalid.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.khalid.calculator.databinding.FragmentCalcBinding
import java.util.logging.Handler

class CalcFragment : Fragment() {

    private lateinit var binding: FragmentCalcBinding
    private lateinit var activityCallback: ToolbarListener

    interface ToolbarListener {
        fun onDigitClicked(digit: Int)
        fun onCClicked()
        fun onAddClicked()
        fun onSubtractClicked()
        fun onMultiplyClicked()
        fun onDivideClicked()
        fun onEqualsClicked()
        fun onModulusClicked()
        fun onSRootClicked()
        fun onSignSwitchClicked()
        fun onDecimalClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            activityCallback = context as ToolbarListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ToolbarListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalcBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val digitButtons = listOf(
            binding.button0, binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6, binding.button7,
            binding.button8, binding.button9
        )
        val buttonC : Button = binding.buttonC
        val buttonAdd : Button = binding.buttonAdd
        val buttonSubtract : Button = binding.buttonSubtract
        val buttonMultiply : Button = binding.buttonMultiply
        val buttonDivide : Button = binding.buttonDivide
        val buttonEquals : Button = binding.buttonEquals!!
        val buttonModulus : Button = binding.buttonPercent
        val buttonSRoot : Button = binding.buttonSquareRoot
        val buttonSignSwitch : Button = binding.buttonReal
        val buttonDecimal : Button = binding.buttonDecimal

        digitButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                activityCallback.onDigitClicked(index)
            }
        }
        buttonC.setOnClickListener {
            activityCallback.onCClicked()
        }
        buttonAdd.setOnClickListener {
            activityCallback.onAddClicked()
        }
        buttonSubtract.setOnClickListener {
            activityCallback.onSubtractClicked()
        }
        buttonMultiply.setOnClickListener {
            activityCallback.onMultiplyClicked()
        }
        buttonDivide.setOnClickListener {
            activityCallback.onDivideClicked()
        }
        buttonEquals.setOnClickListener {
            activityCallback.onEqualsClicked()
        }
        buttonModulus.setOnClickListener {
            activityCallback.onModulusClicked()
        }
        buttonSRoot.setOnClickListener {
            activityCallback.onSRootClicked()
        }
        buttonSignSwitch.setOnClickListener {
            activityCallback.onSignSwitchClicked()
        }
        buttonDecimal.setOnClickListener {
            activityCallback.onDecimalClicked()
        }

    }

}
