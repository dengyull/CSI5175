package com.example.csi5175

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [productdetail1.newInstance] factory method to
 * create an instance of this fragment.
 */
class productdetail1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_productdetail1, container, false)
        val pid = arguments?.getInt("pid",0)
        val db = context?.let { AppDatabase.getAppDatabase(it) }

        var Productname = view.findViewById<TextView>(R.id.productdetails_name)
        var productprice = view.findViewById<TextView>(R.id.productdetails_price)
        var productNumber = view.findViewById<TextView>(R.id.productNumber)
        var productdescription = view.findViewById<TextView>(R.id.productdetails_description)
        var ShareButton = view.findViewById<ImageButton>(R.id.productShareButton)
        var DecreaseButton = view.findViewById<ImageView>(R.id.button_Decrease)
        var increaseButton = view.findViewById<ImageView>(R.id.button_increase)
        var InButton = view.findViewById<Button>(R.id.button_addtocart)

        var Product = pid?.let { db?.productDao()?.findProductByPid(it) }
        Productname.text = Product?.pname
        productprice.text = Product?.price.toString()
        productdescription.text = Product?.description
        ShareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, Product?.pname+": "+Product?.description)
            startActivity(Intent.createChooser(intent, "Share Product via"))

        }
        InButton.setOnClickListener {
            Product?.quantity = productNumber.text.toString().toInt()
            var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            val myuid = sharedPref.getInt("uid", 0)
            var cart = db?.userDao()?.findUserByUid(myuid)?.cart


        }
        DecreaseButton.setOnClickListener {
            productNumber.text = (productNumber.text.toString().toInt()-1).toString()

        }
        increaseButton.setOnClickListener {
            productNumber.text = (productNumber.text.toString().toInt()+1).toString()
        }



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment productdetail1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int, param2: String) =
            productdetail1().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}