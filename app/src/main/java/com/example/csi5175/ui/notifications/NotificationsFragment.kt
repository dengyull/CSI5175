package com.example.csi5175.ui.notifications

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.csi5175.MainActivity
import com.example.csi5175.MainActivity2
import com.example.csi5175.OrderHistory
import com.example.csi5175.R
import com.example.csi5175.backend.model.Address
import com.example.csi5175.backend.model.User
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.databinding.FragmentNotificationsBinding
import com.google.android.material.navigation.NavigationView

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private  lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val firstName_change = binding.firstNameChangeInputs
        val lastName_change = binding.lastNameChangeInputs
        val phone_change = binding.phoneChangeInputs
        val country_change = binding.countryChangeInput
        val state_change = binding.stateChangeInput
        val zipcode_change = binding.zipcodeChangeInput
        val city_change = binding.cityChangeInput
        val street_change = binding.streetChangeInput
        var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        val myuid = sharedPref.getInt("uid", 0)

        val db = context?.let { AppDatabase.getAppDatabase(it) }
        //Toast.makeText(requireContext(), "uid"+myuid.toString(), Toast.LENGTH_LONG).show()
        var user = db?.userDao()?.findUserByUid(myuid)
        //Toast.makeText(requireContext(), "uid"+user?.uid, Toast.LENGTH_LONG).show()
        firstName_change.setText(user?.firstName)
        lastName_change.setText(user?.lastName)
        phone_change.setText(user?.phone.toString())
        country_change.setText(user?.address?.country)
        state_change.setText(user?.address?.state)
        zipcode_change.setText(user?.address?.zipcode)
        city_change.setText(user?.address?.city)
        street_change.setText(user?.address?.street)

        firstName_change.setBackgroundResource(android.R.color.transparent)
        lastName_change.setBackgroundResource(android.R.color.transparent)
        phone_change.setBackgroundResource(android.R.color.transparent)
        country_change.setBackgroundResource(android.R.color.transparent)
        state_change.setBackgroundResource(android.R.color.transparent)
        zipcode_change.setBackgroundResource(android.R.color.transparent)
        city_change.setBackgroundResource(android.R.color.transparent)
        street_change.setBackgroundResource(android.R.color.transparent)
        firstName_change.isEnabled = !firstName_change.isEnabled
        lastName_change.isEnabled = !lastName_change.isEnabled
        phone_change.isEnabled = !phone_change.isEnabled
        country_change.isEnabled = !country_change.isEnabled
        state_change.isEnabled = !state_change.isEnabled
        zipcode_change.isEnabled = !zipcode_change.isEnabled
        city_change.isEnabled = !city_change.isEnabled
        street_change.isEnabled = !street_change.isEnabled

        binding.btnLogout.setOnClickListener{
            sharedPref.edit().remove("uid").apply()
            requireActivity().finish()
            val intents = Intent (getActivity(), MainActivity::class.java)
            startActivity(intents)
        }
        binding.buttonSubmit.setOnClickListener {
            //todo: input type check

            firstName_change.isEnabled = !firstName_change.isEnabled
            lastName_change.isEnabled = !lastName_change.isEnabled
            phone_change.isEnabled = !phone_change.isEnabled
            country_change.isEnabled = !country_change.isEnabled
            state_change.isEnabled = !state_change.isEnabled
            zipcode_change.isEnabled = !zipcode_change.isEnabled
            city_change.isEnabled = !city_change.isEnabled
            street_change.isEnabled = !street_change.isEnabled
            if (firstName_change.isEnabled) {
                // Display underline when the EditText view is enabled
                firstName_change.setBackgroundResource(android.R.drawable.edit_text)
                lastName_change.setBackgroundResource(android.R.drawable.edit_text)
                phone_change.setBackgroundResource(android.R.drawable.edit_text)
                country_change.setBackgroundResource(android.R.drawable.edit_text)
                state_change.setBackgroundResource(android.R.drawable.edit_text)
                zipcode_change.setBackgroundResource(android.R.drawable.edit_text)
                city_change.setBackgroundResource(android.R.drawable.edit_text)
                street_change.setBackgroundResource(android.R.drawable.edit_text)
            } else {
                // Hide underline when the EditText view is disabled
                val addres = Address(country_change.text.toString(),state_change.text.toString(),zipcode_change.text.toString(),city_change.text.toString(),street_change.text.toString())

                val uuser = user?.let { it1 -> User(it1.uid,it1.email,it1.password,firstName_change.text.toString(),lastName_change.text.toString(),phone_change.text.toString().toLong(),addres,it1.history,it1.favorite,it1.cart) }
                if (uuser != null) {
                    db?.userDao()?.updateUserInfo(uuser)
                    user = uuser
                } else {
                    Toast.makeText(requireContext(), "cannot update use infomation", Toast.LENGTH_LONG).show()
                }
                firstName_change.setBackgroundResource(android.R.color.transparent)
                lastName_change.setBackgroundResource(android.R.color.transparent)
                phone_change.setBackgroundResource(android.R.color.transparent)
                country_change.setBackgroundResource(android.R.color.transparent)
                state_change.setBackgroundResource(android.R.color.transparent)
                zipcode_change.setBackgroundResource(android.R.color.transparent)
                city_change.setBackgroundResource(android.R.color.transparent)
                street_change.setBackgroundResource(android.R.color.transparent)
            }
        }

        binding.userOrderHistory.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_notifications_to_orderhistories)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}