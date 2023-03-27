package com.example.csi5175.ui.notifications.modify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentAddresschangeBinding
import com.example.csi5175.databinding.FragmentPhonechangeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PhoneChangeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhoneChangeFragment : Fragment() {

    private var _binding : FragmentPhonechangeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhonechangeBinding.inflate(inflater, container, false)

        binding.phoneChangeConfirm.setOnClickListener {
            binding.phoneChangeInput
        }

        return binding.root
    }

}