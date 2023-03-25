package com.example.csi5175

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.csi5175.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OrderHistoryFirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.orderHistoryCheck.setOnClickListener {//This button is used to the check fragment. "Check"
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.orderHistoryFirstBackButton.setOnClickListener {//This button is used to go back. "Back"

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}