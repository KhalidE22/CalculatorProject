// MainActivity.kt
package com.khalid.calculator

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), CalcFragment.ToolbarListener {

    private var equalsPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDigitClicked(digit: Int) {
        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as TextFragment
        val digitString = digit.toString()
        textFragment.appendText(50, digitString)

        val number2 = textFragment.binding.calcText.text.toString()
        textFragment.setSecondNumber(number2)
    }

    override fun onCClicked() {
        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as TextFragment
        textFragment.changeTextProperties(50, "0")
        textFragment.resetDigitAppended()
    }

    override fun onAddClicked() {
        storeInformation('+')
    }

    override fun onSubtractClicked() {
        storeInformation('-')
    }

    override fun onMultiplyClicked() {
        storeInformation('*')
    }

    override fun onDivideClicked() {
        storeInformation('÷')
    }

    override fun onModulusClicked() {
        Log.d("OnModulusClicked", "Modulus called")
        storeInformation('%')
    }

    override fun onSRootClicked() {
        Log.d("OnSRootClicked", "SRoot called")
        storeInformation('√')

    }
    override fun onSignSwitchClicked() {
        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as? TextFragment
        val currentText = textFragment?.binding?.calcText?.text.toString()

        if (currentText.isNotEmpty() && currentText != "0") {
            val newText = if (currentText.startsWith("-")) {
                currentText.substring(1) // Remove the negative sign if it's already negative
            } else {
                "-$currentText"
            }
            textFragment?.changeTextProperties(50, newText)
        }
    }

    override fun onDecimalClicked() {
        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as TextFragment
        if (!textFragment.isDecimalClicked()) {
            textFragment.appendText(50, ".")
            textFragment.setDecimalClicked(true)

        }
    }

    override fun onEqualsClicked() {
        equalsPressed == true
        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as? TextFragment
        val number2 = textFragment?.binding?.calcText?.text?.toString() ?: ""
        textFragment?.setSecondNumber(number2)
        Log.d("onEqualsClicked", "number2 value: $number2")
        doMath()
    }

    private fun storeInformation(operator: Char) {
        Log.d("storeInformation", "storeInformation Called")
        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as? TextFragment
        val number1 = textFragment?.binding?.calcText?.text?.toString() ?: ""
        val tempOperator = operator.toString()
        // Clear the text fragment so that the user can enter the second number
        if (operator == '+'||operator == '-'||operator == '*'||operator == '÷'||operator == '%') {
            textFragment?.changeTextProperties(50, tempOperator)

        }else if (operator == '%'){
            textFragment?.changeTextProperties(50, "%")
            Log.d("storeInformation", "Clearing Text")
            textFragment?.changeTextProperties(50, "")
        }

        // Store the operator and first number in the text fragment
        textFragment?.setOperator(operator)
        textFragment?.setFirstNumber(number1)
        textFragment?.resetDecimal()

        if (operator == '√'){
            var result = 0f
            var resultToString = "Error"
            result = Math.sqrt(number1.toDouble()).toFloat()
            Log.d("onSRootClicked", "Result: $result")

            resultToString = if (result % 1 == 0f) {
                result.toInt().toString() // If result is an integer, convert it to string without decimal part
            } else {
                result.toString() // If result has decimal part, keep it as it is
            }

            textFragment?.changeTextProperties(50, resultToString)
        }
    }

    private fun doMath() {
        Log.d("doMath", "doMath called")

        val textFragment = supportFragmentManager.findFragmentById(R.id.textFragment) as? TextFragment
        textFragment?.changeTextProperties(50, "")
        val firstNumber = textFragment?.getFirstNumber() ?: 0f
        val secondNumber = textFragment?.getSecondNumber() ?: 0f
        Log.d("doMath", "First Number is: $firstNumber")
        Log.d("doMath", "Second Number is: $secondNumber")
        val operator = textFragment?.getOperator()
        Log.d("doMath", "Operator is: $operator")
        var result = 0f
        var resultToString = "Error"

        when (operator) {
            '+' -> {
                Log.d("doMath", "Operator + recognized")
                result = firstNumber + secondNumber
            }
            '-' -> {
                Log.d("doMath", "Operator - recognized")
                result = firstNumber + secondNumber
            }
            '*' -> {
                Log.d("doMath", "Operator * recognized")
                result = firstNumber * secondNumber
            }
            '÷' -> {
                Log.d("doMath", "Operator ÷ recognized")
                if (secondNumber != 0f) {
                    result = firstNumber / secondNumber
                } else {
                    resultToString = "Error: Division by zero"
                }
                Log.d("doMath", "Result: $result")
            }
            '%' -> {
                Log.d("doMath", "Operator % recognized")
                if (firstNumber % 1 != 0f || secondNumber % 1 != 0f) {
                        resultToString = "Can't modulus a decimal number"
                        textFragment?.changeTextProperties(30, resultToString)
                        Log.d("doMath", "Decimal Number Modulus Detected")
                }else {
                    result = firstNumber % secondNumber
                    Log.d("doMath", "Result: $result")
                    resultToString=result.toInt().toString()
                    textFragment?.changeTextProperties(50, resultToString)
                }
            }
            else -> {
                Log.d("doMath", "Error: Invalid operator")
            }
        }
        if (operator != '%') {
            resultToString = if (result % 1 == 0f) {
                result.toInt().toString() // If result is an integer, convert it to string without decimal part
            } else {
                result.toString() // If result has decimal part, keep it as it is
            }
            textFragment?.changeTextProperties(50, resultToString)
        }

        textFragment?.resetDigitAppended()
    }


}
