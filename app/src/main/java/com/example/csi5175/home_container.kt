package com.example.csi5175

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.databinding.FragmentHomeBinding
import com.example.csi5175.databinding.FragmentHomeContainerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [home_container.newInstance] factory method to
 * create an instance of this fragment.
 */
class home_container : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentHomeContainerBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentHomeContainerBinding.inflate(inflater, container, false)
        val root: View = binding.root
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



        val popularCard = root.findViewById<CardView>(R.id.popular_card)
        val RecyclerViewpopular = root.findViewById<RecyclerView>(R.id.RecyclerView_popular)
        val RecyclerViewfeatured = root.findViewById<RecyclerView>(R.id.RecyclerView_featured)

        val featuredCard = root.findViewById<CardView>(R.id.featured_card)
        val db = context?.let { AppDatabase.getAppDatabase(it) }


        RecyclerViewpopular.layoutManager = LinearLayoutManager(requireContext())
        val myDataset:List<Product> = db?.productDao()?.getAllProduct() ?: listOf()//Todo: favourlist
        Log.v("database", myDataset.toString())
        val adapter = productAdapter(myDataset,
            addClick = {Product ->
                Toast.makeText(requireContext(), "addClick", Toast.LENGTH_LONG).show()
                val newFragment = productdetail1()
                val bundle = Bundle()
                bundle.putInt("pid", Product.pid)
                newFragment.arguments = bundle


                // Get the FragmentManager and start a new transaction
                val fragmentManager = requireActivity().supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                // Replace the existing fragment with the new one
                fragmentTransaction.replace(R.id.home_container, newFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

            },
            plusClick = {Product ->
                Toast.makeText(requireContext(), "plusClick", Toast.LENGTH_LONG).show()
            },
            minusClick = {Product ->
                Toast.makeText(requireContext(), "minusClick", Toast.LENGTH_LONG).show()
            },
            shareClicks = {Product ->
                Toast.makeText(requireContext(), "minusClick", Toast.LENGTH_LONG).show()
            }
        )
        RecyclerViewpopular.adapter = adapter

        RecyclerViewfeatured.layoutManager = LinearLayoutManager(requireContext())
        //val myDatasetfeatured:List<Product> =  listOf()//db?.productDao()?.getAllProduct() ?: listOf()//Todo: featuredlist


        RecyclerViewfeatured.adapter = adapter

        // Set click listeners
        popularCard.setOnClickListener {

        }
        featuredCard.setOnClickListener {

        }


        return root
    }

    fun performSearch(str: String) {
        Log.v("search", str)

        val db = context?.let { AppDatabase.getAppDatabase(it) }
        db?.productDao()?.getProductsWithPrefix(str)

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
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment home_container.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            home_container().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}