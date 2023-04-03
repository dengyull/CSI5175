package com.example.csi5175

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.csi5175.backend.model.Address
import com.example.csi5175.backend.model.Order
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.model.User
import com.example.csi5175.backend.persistence.AppDatabase

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)
        var submitButton = findViewById<Button>(R.id.button_submit)
        var email = findViewById<EditText>(R.id.email_submit_inputs)
        var password = findViewById<EditText>(R.id.password_submit_inputs)
        val firstName = findViewById<EditText>(R.id.firstName_submit_inputs)
        val lastName = findViewById<EditText>(R.id.lastName_submit_inputs)
        val phone = findViewById<EditText>(R.id.phone_submit_inputs)
        val country = findViewById<EditText>(R.id.country_submit_input)
        val state = findViewById<EditText>(R.id.state_submit_input)
        val zipcode = findViewById<EditText>(R.id.zipcode_submit_input)
        val city = findViewById<EditText>(R.id.city_submit_input)
        val street = findViewById<EditText>(R.id.street_submit_input)

        var db = AppDatabase.getAppDatabase(applicationContext)
        submitButton.setOnClickListener{

            //todo: input type check
            if(!isUserNameValid(email.text.toString())){
                Toast.makeText(
                    applicationContext,
                    "Email format is wrong",
                    Toast.LENGTH_LONG
                ).show()
            }else if(!isPasswordValid(password.text.toString())){
                Toast.makeText(
                    applicationContext,
                    "Password must be at least 6 characters",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                when {
                    firstName.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "first name is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    lastName.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "last name is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    phone.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "phone is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    country.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "country is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    state.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "state is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    zipcode.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "zipcode is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    city.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "city is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    street.text.toString().isBlank() -> Toast.makeText(
                        applicationContext,
                        "street is not filled",
                        Toast.LENGTH_LONG
                    ).show()

                    else -> {
                        val addres = Address(
                            country.text.toString(),
                            state.text.toString(),
                            zipcode.text.toString(),
                            city.text.toString(),
                            street.text.toString()
                        )
                        var phonenumber = phone.text.toString().toLong()
                        db?.userDao()?.insert(
                            User(
                                email = email.text.toString(),
                                password = password.text.toString(),
                                firstName = firstName.text.toString(),
                                lastName = lastName.text.toString(),
                                phone = phonenumber,
                                address = addres,
                                history = mutableListOf<Order>(),
                                favorite = listOf<Product>(),
                                cart = listOf<Product>()
                            )
                        )
                        val user = db?.userDao()?.getUserByEmailAndPassword(
                            email.text.toString(),
                            password.text.toString()
                        )

                        var sharedPref: SharedPreferences =
                            PreferenceManager.getDefaultSharedPreferences(this);

                        if (user != null) {
                            sharedPref.edit().putInt("uid", user.uid).apply()
                            Toast.makeText(
                                applicationContext,
                                "register success",
                                Toast.LENGTH_LONG
                            ).show()
                            val resultIntent = Intent()
                            resultIntent.putExtra("result", "registration_successful")
                            setResult(Activity.RESULT_OK, resultIntent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "register fail", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }



                /*if(isInfoNull(firstName.text.toString(), lastName.text.toString(),phone.text.toString(),
                country.text.toString(), state.text.toString(), zipcode.text.toString(),city.text.toString(),
                street.text.toString())){
                Toast.makeText(
                    applicationContext,
                    "Personal information is not filled",
                    Toast.LENGTH_LONG
                ).show()

                 */
            }



        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            false
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length>=6
    }

    /*
    private fun isInfoNull(firstName: String, lastName: String,
                       phone: String, country: String, state: String,
                       zipCode: String, city: String, street: String
    ): Boolean{
        return firstName.isBlank()&&lastName.isBlank()&&phone.isBlank()&&country.isBlank()
                &&state.isBlank()&&zipCode.isBlank()&&city.isBlank()&&street.isBlank()
    }
     */

}