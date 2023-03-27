package com.example.csi5175.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.csi5175.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //These 4 input area is used to modify the personal info.
        //So at the beginning it will be invisible
        binding.userNameModify.isVisible = false
        binding.userEmailModify.isVisible = false
        binding.userAddressModify.isVisible = false
        binding.userPhoneModify.isVisible = false
        binding.userChangeConfirm.isVisible = false

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.userChangeButton.setOnClickListener {

            binding.userNameModify.isVisible = true
            binding.userEmailModify.isVisible = true
            binding.userAddressModify.isVisible = true
            binding.userPhoneModify.isVisible = true
            binding.userChangeConfirm.isVisible = true

            binding.userName.isVisible = false
            binding.userPhone.isVisible = false
            binding.userAddress.isVisible = false
            binding.userEmail.isVisible = false
            binding.userChangeButton.isVisible = false

            //some functions here.
        }

        binding.userChangeConfirm.setOnClickListener {

            binding.userNameModify.isVisible = false
            binding.userEmailModify.isVisible = false
            binding.userAddressModify.isVisible = false
            binding.userPhoneModify.isVisible = false
            binding.userChangeConfirm.isVisible = false

            binding.userName.isVisible = true
            binding.userPhone.isVisible = true
            binding.userAddress.isVisible = true
            binding.userEmail.isVisible = true
            binding.userChangeButton.isVisible = true


            //show the info textview again.
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}