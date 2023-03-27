package com.example.csi5175.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var popularRestaurantsList: RecyclerView
    private lateinit var featuredCuisinesList: RecyclerView
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

// Find the views
        val searchBar = root.findViewById<EditText>(R.id.search_bar)
        val searchIcon = root.findViewById<ImageView>(R.id.search_icon)


        // Set up the search listener
        searchIcon.setOnClickListener {
            val searchTerm = searchBar.text.toString()
            // Perform the search
        }
// Find views
        val popularCard = root.findViewById<CardView>(R.id.popular_card)
        val popularRestaurantImage = root.findViewById<ImageView>(R.id.popular_restaurant_image)
        val popularRestaurantName = root.findViewById<TextView>(R.id.popular_restaurant_name)
        val featuredCard = root.findViewById<CardView>(R.id.featured_card)
        val featuredCuisinesImage = root.findViewById<ImageView>(R.id.featured_cuisines_image)
        val featuredCuisinesName = root.findViewById<TextView>(R.id.featured_cuisines_name)


        // Set click listeners
        popularCard.setOnClickListener {

        }
        featuredCard.setOnClickListener {
            
        }



        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
