package com.example.welcomehome

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    //creating a variable for button "Register"
    private lateinit var register: Button
    //creating variables for validation
    private lateinit var inputName : EditText
    private lateinit var inputSurname : EditText
    private lateinit var inputBirthday : EditText
    private lateinit var inputPasswordText : TextInputEditText
    private lateinit var inputConfPasswordText : TextInputEditText
    private lateinit var inputPasswordLayout : TextInputLayout
    private lateinit var inputConfPasswordLayout : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    // Removes warnings on deprecated methods
        @Suppress("DEPRECATION")

    //Hide StatusBar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)


    //finding Validation
        inputName = findViewById(R.id.inputName)
        inputSurname = findViewById(R.id.inputSurname)
        inputBirthday = findViewById(R.id.inputBirthday)
        inputPasswordText = findViewById(R.id.inputPasswordText)
        inputConfPasswordText = findViewById(R.id.inputConPasswordText)
        inputPasswordLayout = findViewById(R.id.inputPasswordLayout)
        inputConfPasswordLayout = findViewById(R.id.inputConPasswordLayout)

    // Date Picker
        inputBirthday.setOnClickListener{
            val instanceCalendar = Calendar.getInstance()
            val year = instanceCalendar.get(Calendar.YEAR)
            val month = instanceCalendar.get(Calendar.MONTH)
            val day = instanceCalendar.get(Calendar.DAY_OF_MONTH)
            val dialogDatePicker = DatePickerDialog(this,
                {view, yearChoose, monthofYear, dayOfMonth ->
                    val date = (dayOfMonth.toString() + "." + (monthofYear + 1) + "." + year)
                    inputBirthday.setText(date)

                },
                year,
                month,
                day
            )
            dialogDatePicker.show()
        }

    // Calling second screen

        register = findViewById(R.id.buttonRegister)
        register.setOnClickListener{
            val check: Boolean = validate()
            if(check){
                val intent = Intent(this,GreetingsActivity::class.java)
        //Sending name
                intent.putExtra("name","Welcome "+inputName.text.toString()
                        + ", we are happy to have you here with us!")
        //
                startActivity(intent)
            }
            else{
                return@setOnClickListener

            }
        }


    }

    private fun validate(): Boolean {
        val name = inputName.text.toString()
        val surname: String = inputSurname.text.toString()
        val birthday: String = inputBirthday.text.toString()
        val password: String = inputPasswordText.text.toString()
        val confpassword: String = inputConfPasswordText.text.toString()

    //validation
        if(name.isEmpty() || name.length<2){
            showMessage(inputName, "More than 2 characters needed")
            return false
        }
        else if (surname.isEmpty() || surname.length<2){
            showMessage(inputSurname,"More than 2 characters needed")
            return false

        }
        else if (birthday.isEmpty()){
            showMessage(inputBirthday, "This can't be empty")
            return false

        }
        else if(password.isEmpty() || !password.contains(".*[@#$%^&].*".toRegex())){
            inputBirthday.error = null // // Necessary to make the error in InputBirthday drop
            showMessageTextInput(inputPasswordLayout, "Use at least " +
                    "one special character(@#$%^&)")
            return false

        }
        else if(confpassword.isEmpty() || confpassword!=password){
            inputPasswordLayout.error = null // Necessary to make the error in InputPassowordL drop
            showMessageTextInput(inputConfPasswordLayout,"Password doesn't match")
            return false

        }
        else{
            inputConfPasswordLayout.error = null // Error will drop here
            return true
        }

    }
    private fun showMessage(input:EditText,messageEditText:String){
        input.error = messageEditText
        input.requestFocus()
    }
    private fun showMessageTextInput(input: TextInputLayout, messageLayout:String){
        input.error = messageLayout
        input.requestFocus()

    }







}