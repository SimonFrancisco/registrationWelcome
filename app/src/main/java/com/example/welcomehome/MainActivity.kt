package com.example.welcomehome

import android.app.ActionBar.LayoutParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    //creating a variable for button "Register"
    private lateinit var register: View

    //creating variables for validation
    private lateinit var inputName : EditText
    private lateinit var inputSurname : EditText
    private lateinit var inputBirthday : EditText
    private lateinit var inputPassword : EditText
    private lateinit var inputConfPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    // Removes warnings on deprecated methods
        @Suppress("DEPRECATION")

    //Hide StatusBar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

    // Calling second screen
        register = findViewById(R.id.buttonRegister)
        register.setOnClickListener{
            validate()
            val intent = Intent(this,GreetingsActivity::class.java)
            startActivity(intent)
            

        }
    //finding Validation
        inputName = findViewById(R.id.inputName)
        inputSurname = findViewById(R.id.inputSurname)
        inputBirthday = findViewById(R.id.inputBirthday)
        inputPassword = findViewById(R.id.inputPassword)
        inputConfPassword = findViewById(R.id.inputConPassw)

    }

    private fun validate(){
        var name: String = inputName.text.toString()
        var surname: String = inputSurname.text.toString()
        var birthday: String = inputBirthday.text.toString()
        var password: String = inputPassword.text.toString()
        var confpassword: String = inputConfPassword.text.toString()

    //validation

        if(name.isEmpty() || name.length<2){
            showMessage(inputName, "More than 2 characters needed")
        }
        else if (surname.isEmpty() || surname.length<2){
            showMessage(inputName,"More than 2 characters needed")
        }
        else if(password.isEmpty() || !password.contains("@")){
            showMessage(inputPassword, "Weak Password")
        }
        else if(confpassword.isEmpty() || confpassword!=password){
            showMessage(inputConfPassword,"Password doesn't match")
        }

    }
    private fun showMessage(input:EditText,message:String){
        input.error = message
        input.requestFocus()
    }


}