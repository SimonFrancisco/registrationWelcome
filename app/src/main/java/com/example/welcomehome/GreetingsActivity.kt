package com.example.welcomehome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog

class GreetingsActivity : AppCompatActivity()  {
    //creating variables for window

    private lateinit var buttonHello: Button
    private lateinit var sayHello: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greetings)
    // Removes warnings on deprecated methods
        @Suppress("DEPRECATION")

    //Hide StatusBar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

    //Modal Button
        buttonHello = findViewById(R.id.helloButton)
        val intent = intent

        buttonHello.setOnClickListener{
            val dialog = BottomSheetDialog(this)
    //Locating element in another Layout
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
            sayHello = view.findViewById(R.id.sayHello)
    //Getting name form MainActivity
            sayHello.text = intent.getStringExtra("name")
            dialog.setContentView(view)
            dialog.show()

        }



    }
}