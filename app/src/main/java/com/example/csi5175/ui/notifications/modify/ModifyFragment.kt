package com.example.csi5175.ui.notifications.modify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentModifyBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ModifyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ModifyFragment : Fragment() {

    private var _binding: FragmentModifyBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentModifyBinding.inflate(inflater, container, false)

        binding.userAddress.setOnClickListener {
            findNavController().navigate(R.id.action_addresschange_to_info)
        }

        binding.userEmail.setOnClickListener {
            findNavController().navigate(R.id.action_emailchange_to_info)
        }

        binding.userName.setOnClickListener {
            findNavController().navigate(R.id.action_namechange_to_info)

        }

        binding.userPhone.setOnClickListener {
            findNavController().navigate(R.id.action_phonechange_to_info)
        }

        return binding.root
    }

}