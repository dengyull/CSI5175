package com.example.csi5175.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.R
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.databinding.FragmentHomeBinding
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
            //val searchTerm = searchBar.text.toString()
            // Perform the search
            startVoiceInput()
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is changed
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text is changed
                performSearch(searchBar.text.toString())
            }
        }

        searchBar.addTextChangedListener(textWatcher)

        /*only KEYCODE_ENTER will run
        searchBar.setOnEditorActionListener  { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                // The user pressed the enter key, so handle the search action here
                val searchTerm = searchBar.text.toString()
                performSearch(searchTerm)
                true // Return true to indicate that the event was handled
            } else {
                false // Return false to indicate that the event was not handled
            }
        }*/
// Find views
        val popularCard = root.findViewById<CardView>(R.id.popular_card)
        val RecyclerViewpopular = root.findViewById<RecyclerView>(R.id.RecyclerView_popular)
        val RecyclerViewfeatured = root.findViewById<RecyclerView>(R.id.RecyclerView_featured)

        val featuredCard = root.findViewById<CardView>(R.id.featured_card)
        val db = context?.let { AppDatabase.getAppDatabase(it) }


        RecyclerViewpopular.layoutManager = LinearLayoutManager(requireContext())
        val myDataset:List<Product> = db?.productDao()?.getAllProduct() ?: listOf()//Todo: favourlist
        Log.v("database", myDataset.toString())
        val adapter = productAdapter(myDataset)
        RecyclerViewpopular.adapter = adapter

        RecyclerViewfeatured.layoutManager = LinearLayoutManager(requireContext())
        val myDatasetfeatured:List<Product> =  db?.productDao()?.getAllProduct() ?: listOf()//Todo: featuredlist
        val adapterfeatured = productAdapter(myDatasetfeatured)
        RecyclerViewfeatured.adapter = adapterfeatured

        // Set click listeners
        popularCard.setOnClickListener {

        }
        featuredCard.setOnClickListener {
            
        }
        root.findViewById<Button>(R.id.button2).setOnClickListener {

            findNavController().navigate(R.id.action_navigation_home_to_productDetails)
        }



        return root
    }


    fun performSearch(str: String) {
        Log.v("search", str)


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
