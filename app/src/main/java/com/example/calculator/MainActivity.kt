package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var outputTextView: TextView
    var lastNumaric: Boolean= false
    var stateError: Boolean = false
    var lastDot :Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        outputTextView = findViewById(R.id.textView2)
    }

    fun addDigit(view: View)
    {
        if(stateError)
        {
            outputTextView.text=(view as Button).text
            stateError=false
        }else {
            outputTextView.append((view as Button).text)
        }
        lastNumaric=true
    }

    fun addPoint(view: View)
    {
        if(lastNumaric && !stateError && !lastDot)
        {
            outputTextView.append(".")
            lastNumaric=false
            lastDot=true
        }
    }

    fun addSign (view: View)
    {
        if(lastNumaric && !stateError)
        {
            outputTextView.append((view as Button).text)
            lastNumaric=false
            lastDot=false
        }
    }


    fun clear(view: View)
    {
        this.outputTextView.text= ""
        lastNumaric=false
        stateError=false
        lastDot=false
    }

    fun result(view: View)
    {
        if(lastNumaric && !stateError)
        {
            val text = outputTextView.text.toString()
            val expression= ExpressionBuilder(text).build()
            try
            {
                val result= expression.evaluate()
                outputTextView.text= result.toString()
                lastDot=true
            }catch (ex:Exception)
            {
                outputTextView.text="Error"
                stateError=true
                lastNumaric=false
            }
        }

    }
}