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

    fun insertexample(){

        var sharedPref: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this);
        val isload = sharedPref.getInt("isload", 0)
        if (isload==0){
            //todo: example insert
            /*
            var productDao = db?.productDao()
            val labelList1 = ArrayList<String>()
            val labelList2 = ArrayList<String>()
            labelList1.add("good")
            labelList1.add("electronic")
            labelList2.add("watch")
            labelList2.add("usa")
            val calories1 = ArrayList<Double>()
            val calories2 = ArrayList<Double>()
            calories1.add(123.0)
            calories1.add(350.0)
            calories2.add(0.0)
            calories2.add(0.0)
            val product1 = Product(pid = 12, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = labelList1, calories = calories1, sold = 1)
            val product2 = Product(pid = 34, mid = 2, image = null, pname = "watch", description = "good watch", category = "electronic", quantity = 20, price = 123.2, label = labelList2, calories = calories2, sold = 2)
            var merchantDao = db?.merchantDao()
            val l1 = ArrayList<Product>()
            val l2 = ArrayList<Product>()
            l1.add(product1)
            l2.add(product2)
            val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
            val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
            val merchant1 = Merchant(
                mid = 1,
                phoneNumber = 12345,
                rate = 4.5,
                address = address1,
                products = l1
            )
            val merchant2 = Merchant(
                mid = 2,
                phoneNumber = 453543,
                rate = 3.2,
                address = address2,
                products = l2
            )
            merchantDao?.insertAll(merchant1)
            merchantDao?.insertAll(merchant2)
            productDao?.insert(product1)
            productDao?.insert(product2)*/

            sharedPref.edit().putInt("isload", 1).apply()
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