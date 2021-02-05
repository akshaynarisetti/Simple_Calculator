package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {
    var lastNumeric  : Boolean= false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_MyCalculator)
        setContentView(R.layout.activity_main)
    }




  fun onDigit(view: View){

      tvInput.append((view as Button).text)
      lastNumeric = true

      if(tvInput.text.contains("1")){

       }
  }

    fun onClear(view:View){

        tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View){

        if(lastNumeric && !lastDot){

            tvInput.append(".")
            lastDot = true
            lastNumeric = false
        }
    }


    fun onEqual(view: View) {

        if (lastNumeric) {
            var tvValue: String = tvInput.text.toString()
            var prefix = ""
            try {


                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
            } catch (e: ArithmeticException) {

                e.printStackTrace()
            }

                try {

                    if (tvValue.contains("+")) {
                        val splitValue = tvValue.split("+")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if(prefix.isNotBlank()){

                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }

                    if (tvValue.contains("-")) {
                        val splitValue = tvValue.split("-")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if(prefix.isNotBlank()){

                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                    }

                    if (tvValue.contains("X")) {
                        val splitValue = tvValue.split("X")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if(prefix.isNotBlank()){

                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    }

                    if (tvValue.contains("/")) {
                        val splitValue = tvValue.split("/")

                        var one = splitValue[0]
                        var two = splitValue[1]

                        if(prefix.isNotBlank()){

                            one = prefix + one
                        }
                        tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                    }

                }
             catch (e: ArithmeticException) {

                e.printStackTrace()
            }
        }







    }


    private fun removeZeroAfterDot(result: String): String{

        var value = result
        if(result.contains(".0")){

            value  = result.substring(0,result.length - 2)
        }

        return value.toString()
    }

    fun onOperator(view: View){

        if(lastNumeric&& !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }
private fun isOperatorAdded(value: String) : Boolean{

    return if(value.startsWith("-")){

        false
    }else{
        value.contains("/") || value.contains("X") || value.contains("-") || value.contains("+")
    }
}

}