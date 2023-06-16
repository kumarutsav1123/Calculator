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


    fun onOperator(view: View) {}
    fun onEquals(view: View) {}
    fun onDecimal(view: View) {
        if (lastNumeric && !lastDecimal){
            val tvResult = findViewById<TextView>(R.id.tv_result)
            tvResult.append((view as Button).text)
            lastDecimal = true
            lastNumeric = false
        }

    }


    /*
    fun integerInput(tvResult: TextView): Int{
        val btnZero = findViewById<Button>(R.id.btn_0)
        val btnOne = findViewById<Button>(R.id.btn_1)
        val btnTwo = findViewById<Button>(R.id.btn_2)
        val btnThree = findViewById<Button>(R.id.btn_3)
        val btnFour = findViewById<Button>(R.id.btn_4)
        val btnFive = findViewById<Button>(R.id.btn_5)
        val btnSix = findViewById<Button>(R.id.btn_6)
        val btnSeven = findViewById<Button>(R.id.btn_7)
        val btnEight = findViewById<Button>(R.id.btn_8)
        val btnNine = findViewById<Button>(R.id.btn_9)

        var integerInput = 0;
        btnZero.setOnClickListener {
            tvResult.text = "0"
            integerInput = 0
        }
        btnOne.setOnClickListener {
            tvResult.text = "1"
            integerInput = 1
        }

        return integerInput;
    }
*/

}