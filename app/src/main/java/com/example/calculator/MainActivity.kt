package com.example.calculator

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    var currentInput = ""
    var operator = ""
    var firstNum = 0.0

    // Fungsi yang dipanggil saat awal apk dibuka
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpButton();
    }
    private fun setUpButton(){
        binding.zero.setOnClickListener { addToInput("0")} //value harus berupa string biar ga ketambah langsung
        binding.one.setOnClickListener { addToInput("1")}
        binding.two.setOnClickListener { addToInput("2")}
        binding.three.setOnClickListener { addToInput("3")}
        binding.four.setOnClickListener { addToInput("4")}
        binding.five.setOnClickListener { addToInput("5")}
        binding.six.setOnClickListener { addToInput("6")}
        binding.seven.setOnClickListener { addToInput("7")}
        binding.eight.setOnClickListener { addToInput("8")}
        binding.nine.setOnClickListener { addToInput("9")}
        binding.sum.setOnClickListener { setOp("+")}
        binding.substract.setOnClickListener { setOp("-")}
        binding.multiple.setOnClickListener { setOp("*")}
        binding.divide.setOnClickListener { setOp("/")}
        binding.equals.setOnClickListener { calculateResult()}
        binding.clear.setOnClickListener { clearInput() }

    }
    private fun addToInput(value: String){
        currentInput += value //current input berisi barisan teks
        binding.input1.text = currentInput //Memasukan current input ke text input1
    }
    private fun setOp(op : String){
        if (currentInput.isNotEmpty()){
            firstNum = currentInput.toDouble()
            operator = op
            currentInput = ""
        }
    }
    private fun calculateResult (){
        if (currentInput.isNotEmpty() && operator.isNotEmpty()){
            val secondNum = currentInput.toDouble()
            val result = when (operator){ //switch case dalam java
                "+" -> firstNum + secondNum
                "-" -> firstNum - secondNum
                "*" -> firstNum * secondNum
                "/" ->  if (secondNum != 0.0) firstNum / secondNum else "Error" //else /
                else -> "Error" //else when

            }

            binding.input1.text = result.toString()
            currentInput=""
            operator=""
        }
    }
    private fun clearInput() {
        currentInput = ""
        operator = ""
        firstNum = 0.0
        binding.input1.text = "0"
    }
}