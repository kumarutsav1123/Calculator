package com.krutsav.s23calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var lastNumeric: Boolean = false
    var lastDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun onDigit(view: View) {
        val tvResult = findViewById<TextView>(R.id.tv_result)
        tvResult.append((view as Button).text)

        lastNumeric = true
    }

    fun onClear(view: View){
        val tvResult = findViewById<TextView>(R.id.tv_result)
        tvResult.text = ""

        lastDecimal = false
        lastNumeric = false
    }

    fun onDelete(view: View) {
        val tvResult = findViewById<TextView>(R.id.tv_result)

        // App crashes if tvResult is empty - index out of bound, handled
        val tvLength = tvResult.length()
        if (tvLength > 0){
            val deletedChar: Char = tvResult.text.get(tvLength - 1)
            tvResult.text = tvResult.text.substring(0, tvResult.length()-1)
            if (deletedChar == '.'){
                lastDecimal = false
                if (tvLength-1 != 0){
                    lastNumeric = true
                }
            }
        }
    }

//TODO: Presently negative numbers cannot be worked out with, implement it later, as it'll create many new cases
    fun onOperator(view: View) {
        val tvResult = findViewById<TextView>(R.id.tv_result)
        val str = tvResult.text.toString()
        if (lastNumeric && !containsOperator(str)){
            tvResult.append((view as Button).text)
            lastNumeric = false
            lastDecimal = false
        }
    }

    private fun containsOperator(str: String): Boolean{
        if (str.startsWith('-')){
            return false
        }
        else {
            return  str.contains('+') ||
                    str.contains('-') ||
                    str.contains('X') ||
                    str.contains('%') ||
                    str.contains('/')
        }
    }

    fun onEquals(view: View) {
        val tvResult = findViewById<TextView>(R.id.tv_result)
        val value = tvResult.text
        var result:String
        val splitedText:List<String>
        if (value.contains('+')){
            splitedText = value.split('+')
            val valueOne = splitedText[0].toDouble()
            val valueTwo = splitedText[1].toDouble()
            result = (valueOne + valueTwo).toString()
        }
        else if (value.contains('-')){
            splitedText = value.split('-')
            val valueOne = splitedText[0].toDouble()
            val valueTwo = splitedText[1].toDouble()
            result = (valueOne - valueTwo).toString()
        }
        else if (value.contains('X')){
            splitedText = value.split('X')
            val valueOne = splitedText[0].toDouble()
            val valueTwo = splitedText[1].toDouble()
            result = (valueOne * valueTwo).toString()
        }
        else if (value.contains('/')){
            splitedText = value.split('/')
            val valueOne = splitedText[0].toDouble()
            val valueTwo = splitedText[1].toDouble()
            try{
                result = (valueOne / valueTwo).toString()
            }
            catch (e: ArithmeticException){
                Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                result = "Infinity"
            }
        }
        else if (value.contains('%')){
            splitedText = value.split('%')
            val valueOne = splitedText[0].toDouble()
            val valueTwo = splitedText[1].toDouble()

            result = (valueOne % valueTwo).toString()
        }
        else {
            result = value.toString()
        }
        tvResult.text = result
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDecimal){
            val tvResult = findViewById<TextView>(R.id.tv_result)
            tvResult.append((view as Button).text)
            lastDecimal = true
            lastNumeric = false
        }

    }
}