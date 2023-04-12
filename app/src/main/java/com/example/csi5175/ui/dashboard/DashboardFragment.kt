package com.example.csi5175.ui.dashboard

import android.app.AlertDialog
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
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private var db:AppDatabase? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var myDataset = mutableListOf<Product>()
    private var myuid by Delegates.notNull<Int>()
    private lateinit var adapter:CheckOutAdapter

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

        //var pricenumber = 0.0//allprice.text.toString().toDouble()
        var productnum = 0
        var totalcal1 = 0.0
        var totalcal2 = 0.0
        val calories = root.findViewById<TextView>(R.id.tv_calories)

        var dec: BigDecimal = BigDecimal.ZERO

        var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        myuid = sharedPref.getInt("uid", 0)
        db = context?.let { AppDatabase.getAppDatabase(it) }
        RecyclerViewpopular.layoutManager = LinearLayoutManager(requireContext())
        myDataset.addAll(db?.userDao()?.findUserByUid(myuid)?.cart ?: listOf())
        for(ds in myDataset){
            dec = dec.add(BigDecimal.valueOf(ds.price).multiply(BigDecimal(ds.quantity)))
            productnum += ds.quantity
            if(ds?.calories?.size == 1){
                totalcal1 += ds?.calories?.get(0)!!
                totalcal2 += ds?.calories?.get(0)!!
            } else if(ds?.calories?.size == 2) {
                totalcal1 += ds?.calories?.get(0)!!
                totalcal2 += ds?.calories?.get(1)!!
            } else {
            }
        }

        if(totalcal1 == 0.0 && totalcal2==0.0) {
            calories.text = "calories above 0"
        } else if(totalcal2==0.0) {
            calories.text = "calories above " + totalcal1
        } else {
            calories.text = "calories above " + totalcal1 + " to " + totalcal2
        }


        productsum.text = "total "+productnum.toString()+ " product"
        allprice.text = "total price: "+ dec.toString()
        adapter = CheckOutAdapter(myDataset,
            plusClick = {Product, Position ->
                myDataset[Position].quantity += 1
                dec = dec.add(BigDecimal.valueOf(Product.price))
                productnum += 1
                productsum.text = "total "+productnum.toString()+ " product"
                allprice.text = "total price: "+ dec.toString()
            },
            minusClick = {Product, Position ->

                val nim =  myDataset[Position].quantity - 1
                if(nim==0){

                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Remove Item")
                    builder.setMessage("Do you want to remove this item?")
                    builder.setPositiveButton("ok") { dialog, which ->
                        // Handle positive button click
                        myDataset.removeAt(Position)
                        not(Position)
                    }
                    builder.setNegativeButton("no") { dialog, which ->
                        // Handle positive button click
                    }
                    val dialog = builder.create()
                    dialog.show()


                } else{
                    myDataset[Position].quantity -= 1
                }
                dec = dec.subtract(BigDecimal.valueOf(Product.price))
                productnum -= 1
                productsum.text = "total "+productnum.toString()+ " product"
                allprice.text = "total price: "+ dec.toString()
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
            var order = Order(uid = myuid, dateTime = Date(), list = myDataset, price = dec.toDouble())
            db?.orderDao()?.insert(order)
            val newhistory = db?.orderDao()?.getAllOrdersByUid(myuid) as MutableList<Order>

            user?.history= newhistory
            if (user != null) {
                user.cart = mutableListOf<Product>()
                db?.userDao()?.updateUserInfo(user)

                myDataset.clear()
                adapter.notifyDataSetChanged()
                dec = BigDecimal.valueOf(0)
                productnum = 0
                productsum.text = "total "+productnum.toString()+ " product"
                allprice.text = "total price: "+ dec.toString()
                calories.text = "calories above 0"

            }

        }
        return root
    }

    fun not(Position:Int){

        adapter.notifyItemRemoved(Position)
        adapter.notifyDataSetChanged()
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
