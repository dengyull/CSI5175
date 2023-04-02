package com.example.csi5175

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.backend.model.Order
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.databinding.FragmentBrowseBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [orderhistories.newInstance] factory method to
 * create an instance of this fragment.
 */
class orderhistories : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var orderhisto:List<Order>
    private lateinit var RecyclerViewtotal: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_orderhistories, container, false)
        var textView17 =view.findViewById<TextView>(R.id.textView17)

        val db = context?.let { AppDatabase.getAppDatabase(it) }
        var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        val uid = sharedPref.getInt("uid", 0)
        if (db != null) {
            var user = db.userDao().findUserByUid(uid)
            Toast.makeText(requireContext(), "uid"+uid, Toast.LENGTH_LONG).show()
            if(user.history!= null){
                orderhisto = user.history!!

            }
            var texts = ""
            for (or in user.history!!){

                texts = texts + or
            }
            textView17.text = texts
        } else{
            orderhisto = listOf()
        }
        orderhisto = listOf()
        RecyclerViewtotal = view.findViewById<RecyclerView>(R.id.RecyclerView_order)



        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RecyclerViewtotal.layoutManager = LinearLayoutManager(requireContext())
        val adapter = OrderAdapter(orderhisto)
        RecyclerViewtotal.adapter = adapter
        // Other initialization code here
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment orderhistories.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            orderhistories().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}