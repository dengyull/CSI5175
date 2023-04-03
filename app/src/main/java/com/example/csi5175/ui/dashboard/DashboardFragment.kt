package com.example.csi5175.ui.dashboard

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.CheckOutAdapter
import com.example.csi5175.ProductDetails
import com.example.csi5175.R
import com.example.csi5175.backend.model.Order
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.databinding.FragmentDashboardBinding
import com.example.csi5175.productAdapter
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.properties.Delegates

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private var db:AppDatabase? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var myDataset:List<Product>
    private var myuid by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val checkoutButton = root.findViewById<Button>(R.id.btn_commit_checkout)
        val RecyclerViewpopular = root.findViewById<RecyclerView>(R.id.RecyclerView_checkout)
        val allprice = root.findViewById<TextView>(R.id.tv_allPrice)
        val productsum = root.findViewById<TextView>(R.id.tv_allGoodsNum)

        var pricenumber = 0.0//allprice.text.toString().toDouble()
        var productnum = 0

        var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        myuid = sharedPref.getInt("uid", 0)
        db = context?.let { AppDatabase.getAppDatabase(it) }
        RecyclerViewpopular.layoutManager = LinearLayoutManager(requireContext())
        myDataset = db?.userDao()?.findUserByUid(myuid)?.cart ?: listOf()//Todo: favourlist
        for(ds in myDataset){
            pricenumber+=ds.price * ds.quantity
            productnum += ds.quantity
        }
        productsum.text = "total "+productnum.toString()+ " product"
        allprice.text = "total price: "+ pricenumber.toString()
        val adapter = CheckOutAdapter(myDataset,
            plusClick = {Product ->
                pricenumber+=Product.price
                productnum += 1
                productsum.text = "total "+productnum.toString()+ " product"
                allprice.text = "total price: "+ pricenumber.toString()
            },
            minusClick = {Product ->
                pricenumber-=Product.price
                productnum -= 1
                productsum.text = "total "+productnum.toString()+ " product"
                allprice.text = "total price: "+ pricenumber.toString()
            })
        RecyclerViewpopular.adapter = adapter
        // Set onClickListener for orderHistoryButton

        // Set onClickListener for checkoutButton
        checkoutButton.setOnClickListener {
            // Handle button click
            //todo: clear cart and inser to history
            //
            //val history = db?.userDao().insertOrder()
            Toast.makeText(requireContext(), "checkoutButton", Toast.LENGTH_LONG).show()
            val user = db?.userDao()?.findUserByUid(myuid)
            val newhistory = mutableListOf<Order>()
            if (user != null) {
                user.history?.let { it1 -> newhistory.addAll(it1) }
            }
            newhistory.add(Order(uid = myuid, dateTime = Date(), list = myDataset, price = pricenumber))

            user?.history= newhistory
            if (user != null) {
                user.cart = mutableListOf<Product>()
                db?.userDao()?.updateUserInfo(user)

                myDataset = mutableListOf<Product>()
                RecyclerViewpopular.adapter = CheckOutAdapter(myDataset,
                    plusClick = {Product ->
                        Toast.makeText(requireContext(), "plusClick", Toast.LENGTH_LONG).show()
                        pricenumber+=Product.price
                        productnum += 1
                        productsum.text = "total "+productnum.toString()+ " product"
                        allprice.text = "total price: "+ pricenumber.toString()
                    },
                    minusClick = {Product ->

                        Toast.makeText(requireContext(), "minusClick", Toast.LENGTH_LONG).show()
                        pricenumber-=Product.price
                        productnum -= 1
                        productsum.text = "total "+productnum.toString()+ " product"
                        allprice.text = "total price: "+ pricenumber.toString()
                    })

            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        val user = db?.userDao()?.findUserByUid(myuid)
        if (user != null) {
            user.cart = myDataset
            db?.userDao()?.updateUserInfo(user)

        }

        _binding = null
    }
}
