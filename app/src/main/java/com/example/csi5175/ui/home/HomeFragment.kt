package com.example.csi5175.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.R
import com.example.csi5175.databinding.FragmentHomeBinding
import com.example.csi5175.model.Product
import com.example.csi5175.persistence.AppDatabase
import com.example.csi5175.productAdapter

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

        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Handle search action here
                true
            } else {
                false
            }
        }
// Find views
        val popularCard = root.findViewById<CardView>(R.id.popular_card)
        val RecyclerViewpopular = root.findViewById<RecyclerView>(R.id.RecyclerView_popular)
        val RecyclerViewfeatured = root.findViewById<RecyclerView>(R.id.RecyclerView_featured)

        val featuredCard = root.findViewById<CardView>(R.id.featured_card)
        val featuredCuisinesImage = root.findViewById<ImageView>(R.id.featured_cuisines_image)
        val featuredCuisinesName = root.findViewById<TextView>(R.id.featured_cuisines_name)


        RecyclerViewpopular.layoutManager = LinearLayoutManager(requireContext())
        val myDataset:List<Product> =  listOf()//Todo: favourlist
        val adapter = productAdapter(myDataset)
        RecyclerViewpopular.adapter = adapter

        RecyclerViewfeatured.layoutManager = LinearLayoutManager(requireContext())
        val myDatasetfeatured:List<Product> =  listOf()//Todo: featuredlist
        val adapterfeatured = productAdapter(myDatasetfeatured)
        RecyclerViewpopular.adapter = adapterfeatured

        // Set click listeners
        popularCard.setOnClickListener {

        }
        featuredCard.setOnClickListener {
            
        }



        return root
    }



    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now")
        resultLauncher.launch(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val spokenText = result!![0]
            binding.searchBar.setText(spokenText)
            // todo: start search

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
