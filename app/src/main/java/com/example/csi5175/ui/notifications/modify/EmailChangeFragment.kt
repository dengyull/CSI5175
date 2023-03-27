package com.example.csi5175.ui.notifications.modify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentAddresschangeBinding
import com.example.csi5175.databinding.FragmentEmailchangeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EmailChangeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmailChangeFragment : Fragment() {

    private var _binding : FragmentEmailchangeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEmailchangeBinding.inflate(inflater, container, false)

        binding.emailChangeConfirm.setOnClickListener {
            binding.emailChangeInput
            findNavController().navigate(R.id.action_emailchange_to_info)
        }

        return binding.root

    }

}

