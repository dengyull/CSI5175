package com.example.csi5175

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.csi5175.backend.model.*
import com.example.csi5175.backend.persistence.AppDatabase
import java.io.ByteArrayOutputStream

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
                            insertexample()
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

            val db = AppDatabase.getAppDatabase(applicationContext)
            val productDao = db?.productDao()
            // 1
            val labelList1 = ArrayList<String>()
            labelList1.add("Coffee")
            labelList1.add("Afternoon tea")
            val calories1 = ArrayList<Double>()
            calories1.add(0.0)
            calories1.add(10.0)
            val image1 = R.drawable.iced_coffee_cocktail
            val product1 = Product(mid = 1, image = image1, pname = "Ice coffee", description = "A good taste of summer", category = "Drink", quantity = 999, price = 4.25, label = labelList1, calories = calories1, sold = 0)


            // 2
            val labelList2 = ArrayList<String>()
            labelList2.add("Cake")
            labelList2.add("Afternoon tea")
            val calories2 = ArrayList<Double>()
            calories2.add(250.0)
            calories2.add(300.0)
            val image2 = R.drawable.devils_food_cake_123456789_1_of_1_500x500
            val product2 = Product(mid = 1, image = image2, pname = "Chocolate cake", description = "sweet lover", category = "Eat", quantity = 999, price = 10.25, label = labelList2, calories = calories2, sold = 0)


            // 3

            val labelList3 = ArrayList<String>()
            labelList3.add("Fast food")
            labelList3.add("High calories")
            val calories3 = ArrayList<Double>()
            calories3.add(350.0)
            calories3.add(400.0)
            val image3 = R.drawable.french_fries_recipe_500x500
            val product3 = Product(mid = 2, image = image3, pname = "French Fries", description = "Crispy with flavor", category = "Eat", quantity = 999, price = 9.95, label = labelList3, calories = calories3, sold = 0)


            // 4

            val labelList4 = ArrayList<String>()
            labelList4.add("Ice cream")
            labelList4.add("High calories")
            val calories4 = ArrayList<Double>()
            calories4.add(250.0)
            calories4.add(300.0)
            val image4 = R.drawable.matcha_ice_cream_22_500x500
            val product4 = Product(mid = 3, image = image4, pname = "Matcha ice cream", description = "Cool summer", category = "Eat", quantity = 999, price = 7.65, label = labelList4, calories = calories4, sold = 0)

            // 4

            val labelList5 = ArrayList<String>()
            labelList5.add("Bubble tea")
            labelList5.add("High calories")
            val calories5 = ArrayList<Double>()
            calories5.add(150.0)
            calories5.add(200.0)
            val image5 = R.drawable.brown_sugar_bubble_milk_tea_500x500
            val product5 = Product(mid = 4, image = image5, pname = "Bubble tea", description = "Add a bit sweetness", category = "Drink", quantity = 999, price = 5.65, label = labelList5, calories = calories5, sold = 0)


            val merchantDao = db?.merchantDao()
            val l1 = ArrayList<Product>()
            val l2 = ArrayList<Product>()
            val l3 = ArrayList<Product>()
            val l4 = ArrayList<Product>()
            l1.add(product1)
            l1.add(product2)
            l2.add(product3)
            l3.add(product4)
            l4.add(product5)

            val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
            val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
            val address3 = Address(country = "Canada", state = "ON", zipcode = "K1K3N6", city = "Ottawa", street = "15 King Ave")
            val address4 = Address(country = "Canada", state = "ON", zipcode = "K1K3N6", city = "Ottawa", street = "3 Cooper St")
            val merchant1 = Merchant(
                mid = 1,
                phoneNumber = 4124561245,
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
            val merchant3 = Merchant(
                mid = 3,
                phoneNumber = 453543,
                rate = 3.2,
                address = address3,
                products = l2
            )
            val merchant4 = Merchant(
                mid = 4,
                phoneNumber = 453543,
                rate = 4.0,
                address = address4,
                products = l2
            )
            merchantDao?.insertAll(merchant1, merchant2, merchant3, merchant4)
            productDao?.insert(product1, product2, product3, product4, product5)

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