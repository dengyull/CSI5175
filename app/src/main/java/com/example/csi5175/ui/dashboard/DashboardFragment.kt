package com.example.csi5175.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.CheckOutAdapter
import com.example.csi5175.ProductDetails
import com.example.csi5175.R
import com.example.csi5175.backend.model.Product
import com.example.csi5175.backend.persistence.AppDatabase
import com.example.csi5175.databinding.FragmentDashboardBinding
import com.example.csi5175.productAdapter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private var db:AppDatabase? = null
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
        val checkoutButton = root.findViewById<Button>(R.id.btn_commit)
        val RecyclerViewpopular = root.findViewById<RecyclerView>(R.id.RecyclerView_checkout)

        db = context?.let { AppDatabase.getAppDatabase(it) }
        RecyclerViewpopular.layoutManager = LinearLayoutManager(requireContext())
        val myDataset:List<Product> = db?.userDao()?.findUserByUid(1)?.cart ?: listOf()//Todo: favourlist
        Log.v("database", myDataset.toString())
        val adapter = CheckOutAdapter(myDataset)
        RecyclerViewpopular.adapter = adapter
        // Set onClickListener for orderHistoryButton
        checkoutButton.setOnClickListener {
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
            // Create a new instance of the fragment you want to navigate to

            //findNavController().navigate(R.id.action_navigation_dashboard_to_productdetail)

        }
        // Set onClickListener for checkoutButton
        checkoutButton.setOnClickListener {
            // Handle button click
            //todo: clear cart and inser to history
            //
            //val history = db?.userDao().insertOrder()

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
