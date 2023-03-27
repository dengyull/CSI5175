package com.example.csi5175.ui.notifications.modify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentAddresschangeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AddressChangeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddressChangeFragment : Fragment() {

    private var _binding :FragmentAddresschangeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddresschangeBinding.inflate(inflater, container, false)

        binding.addressChangeConfirm.setOnClickListener {
            binding.addressChangeInput //The input area
            findNavController().navigate(R.id.action_addresschange_to_info)
        }

        return binding.root

    }


}
