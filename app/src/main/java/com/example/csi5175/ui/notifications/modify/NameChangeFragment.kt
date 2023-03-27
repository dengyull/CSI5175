package com.example.csi5175.ui.notifications.modify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentAddresschangeBinding
import com.example.csi5175.databinding.FragmentNamechangeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NameChangeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NameChangeFragment : Fragment() {

    private var _binding : FragmentNamechangeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNamechangeBinding.inflate(inflater, container, false)

        binding.nameChangeConfirm.setOnClickListener {
            binding.nameChangeInput
        }

        return binding.root
    }

}
