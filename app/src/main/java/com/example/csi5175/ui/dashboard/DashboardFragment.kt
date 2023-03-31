package com.example.csi5175.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
    // Find views by their IDs
        val orderHistoryButton =root.findViewById<Button>(R.id.button_orderhistory)
        val restaurantTitleTextView = root.findViewById<TextView>(R.id.restaurant_title)
        val priceTextView = root.findViewById<TextView>(R.id.price)
        val cartCardView = root.findViewById<CardView>(R.id.cart_card)
        val detailsRestaurantTitle = root.findViewById<TextView>(R.id.details_restaurant_title)
        val detailsFood = root.findViewById<TextView>(R.id.details_food)
        val totalPrice = root.findViewById<TextView>(R.id.Total_price)
        val checkoutButton = root.findViewById<Button>(R.id.button_checkout)

        val showdetails_button = root.findViewById<ImageView>(R.id.showdetails_button)
        val details_card = root.findViewById<CardView>(R.id.details_card)
        showdetails_button.setOnClickListener {
            // 将details_card设置为可见
            details_card.visibility = View.VISIBLE
            detailsRestaurantTitle.text = restaurantTitleTextView.text
            // Set detailsFood text
            detailsFood.text = "Food details here!"
            // Set totalPrice text
            totalPrice.text = priceTextView.text
        }
        // Set onClickListener for orderHistoryButton
        orderHistoryButton.setOnClickListener {
            // Handle button click
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "https://www.example.com")
            startActivity(Intent.createChooser(intent, "Share link via"))
            /*
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.my_image)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/"
            val uri = getImageUri(bitmap)
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(intent, "Share image via"))*/


        }
        // Set onClickListener for checkoutButton
        checkoutButton.setOnClickListener {
            // Handle button click
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
