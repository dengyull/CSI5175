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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.csi5175.backend.model.Address
import com.example.csi5175.backend.model.Merchant
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.databinding.FragmentBrowseBinding
import com.example.csi5175.databinding.FragmentBrowseContainerBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var RecyclerViewtotal:RecyclerView
private lateinit var adapter: productAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [browse_container.newInstance] factory method to
 * create an instance of this fragment.
 */
class browse_container : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentBrowseContainerBinding? = null
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
        // Inflate the layout for this fragment
        _binding = FragmentBrowseContainerBinding.inflate(inflater,container,false)
        val root: View = binding.root

        val searchBar = root.findViewById<EditText>(R.id.search_bar)
        val searchIcon = root.findViewById<ImageView>(R.id.search_icon)
        RecyclerViewtotal = root.findViewById<RecyclerView>(R.id.RecyclerView_total)
        val db = context?.let { AppDatabase.getAppDatabase(it) }


        RecyclerViewtotal.layoutManager = LinearLayoutManager(requireContext())
        val myDataset:List<Product> = db?.productDao()?.getAllProduct() ?: listOf()//Todo: favourlist
        Log.v("database", myDataset.toString())
        adapter = adapterr(myDataset)
        RecyclerViewtotal.adapter = adapter

        // Set up the search listener
        searchIcon.setOnClickListener {
            val searchTerm = searchBar.text.toString()
            // Perform the search
        }
        root.findViewById<Button>(R.id.button3).setOnClickListener {
            fun setup() {
                // Memory database is created for testing purpose only
                // In the real application, the db should be created by call AppDatabase.getAppDatabase(context = ...) in the main activity upon onCreate
                // Once db is created, to interact with db, it is needed to acquire the dao from the db.
                var productDao = db?.productDao()
                val labelList1 = ArrayList<String>()
                val labelList2 = ArrayList<String>()
                labelList1.add("good")
                labelList1.add("electronic")
                labelList2.add("watch")
                labelList2.add("usa")
                val calories1 = ArrayList<Double>()
                val calories2 = ArrayList<Double>()
                calories1.add(123.0)
                calories1.add(350.0)
                calories2.add(0.0)
                calories2.add(0.0)
                val product1 = Product(pid = 12, mid = 1, image = null, pname = "camera", description = "good cam", category = "electronic", quantity = 20, price = 43123.1, label = labelList1, calories = calories1, sold = 1)
                val product2 = Product(pid = 34, mid = 2, image = null, pname = "watch", description = "good watch", category = "electronic", quantity = 20, price = 123.2, label = labelList2, calories = calories2, sold = 2)
                var merchantDao = db?.merchantDao()
                val l1 = ArrayList<Product>()
                val l2 = ArrayList<Product>()
                l1.add(product1)
                l2.add(product2)
                val address1 = Address(country = "USA", state = "PA", zipcode = "15222", city = "Pittsburgh", street = "316 4th Ave")
                val address2 = Address(country = "Canada", state = "ON", zipcode = "K1S0X7", city = "Ottawa", street = "47 Laurier St")
                val merchant1 = Merchant(
                    mid = 1,
                    phoneNumber = 12345,
                    rate = 4.5,
                    address = address1,
                    products = l1
                )
                val merchant2 = Merchant(
                    mid = 2,
                    phoneNumber = 453543,
                    rate = 3.2,
                    address = address2,
                    products = l2
                )
                merchantDao?.insertAll(merchant1)
                merchantDao?.insertAll(merchant2)
                productDao?.insert(product1)
                productDao?.insert(product2)
            }

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
        searchIcon.setOnClickListener {
            //val searchTerm = searchBar.text.toString()
            // Perform the search
            startVoiceInput()
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
    fun performSearch(str: String) {
        Log.v("search", str)

        val db = context?.let { AppDatabase.getAppDatabase(it) }
        var ss =db?.productDao()?.getProductsWithPrefix(str)

        RecyclerViewtotal.adapter = ss?.let {
            adapterr(ss)
        }

    }

    fun adapterr(it: List<Product>): productAdapter {
        return productAdapter(it,
            object : productAdapter.OnItemClickListener{
                override fun onItemClick(position: Product) {
                    // Handle item click
                    Toast.makeText(requireContext(), "addClick", Toast.LENGTH_LONG).show()
                    val newFragment = productdetail1()
                    val bundle = Bundle()
                    bundle.putInt("pid", position.pid)
                    newFragment.arguments = bundle


                    // Get the FragmentManager and start a new transaction
                    val fragmentManager = requireActivity().supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()

                    // Replace the existing fragment with the new one
                    fragmentTransaction.replace(R.id.home_container, newFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }

            },
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
                fragmentTransaction.replace(R.id.browse_containers, newFragment)
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
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment browse_container.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            browse_container().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}