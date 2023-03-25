package com.example.csi5175.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
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

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

// Find the views
        val searchBar = root.findViewById<EditText>(R.id.search_bar)
        val searchIcon = root.findViewById<ImageView>(R.id.search_icon)
        popularRestaurantsList = root.findViewById(R.id.popular_restaurants_list)
        featuredCuisinesList = root.findViewById(R.id.featured_cuisines_list)

        // Set up the search listener
        searchIcon.setOnClickListener {
            val searchTerm = searchBar.text.toString()
            // Perform the search
        }

        // Set up the popular restaurants section
        val popularRestaurantsAdapter = PopularRestaurantsAdapter()
        popularRestaurantsList.adapter = popularRestaurantsAdapter
        popularRestaurantsList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        // Populate the adapter with data

        // Set up the featured cuisines section
        val featuredCuisinesAdapter = FeaturedCuisinesAdapter()
        featuredCuisinesList.adapter = featuredCuisinesAdapter
        featuredCuisinesList.layoutManager = LinearLayoutManager(activity)
        // Populate the adapter with data


        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
