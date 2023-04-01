package com.example.csi5175

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.csi5175.backend.model.Product
import com.example.csi5175.databinding.FragmentBrowseBinding
import java.io.ByteArrayOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentBrowseBinding? = null
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
        _binding = FragmentBrowseBinding.inflate(inflater,container,false)
        val view = inflater.inflate(R.layout.fragment_productdetails, container, false)

        val root: View = binding.root

        val ProductDetailsImage = root.findViewById<ImageView>(R.id.image_product_details)
        val ProductDetailsName = root.findViewById<TextView>(R.id.productdetails_name)
        val ProductDetailsPrice= root.findViewById<TextView>(R.id.productdetails_price)
        val ProductDetailsDescription = root.findViewById<TextView>(R.id.productdetails_description)

        val buttonIncrease = view.findViewById<ImageView>(R.id.button_increase)
        val buttonDecrease = view.findViewById<ImageView>(R.id.button_Decrease)
        val buttonAddToCart = view.findViewById<Button>(R.id.button_addtocart)
        val shareButtons = view.findViewById<ImageButton>(R.id.imageButton)
        shareButtons.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "https://www.example.com")
            startActivity(Intent.createChooser(intent, "Share link via"))

            /*val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cart)

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/"
            //intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile))
            intent.setPackage("com.instagram.android")
            try {
                context?.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Instagram not installed", Toast.LENGTH_SHORT).show()
            }*/
        }

        // Set an onClick listener to the button
        buttonIncrease.setOnClickListener {

        }
        buttonDecrease.setOnClickListener {

        }
        buttonAddToCart.setOnClickListener {

        }
        return inflater.inflate(R.layout.fragment_productdetails, container, false)
    }
    fun getImageUri(context: Context, image: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, image, "Title", null)
        return Uri.parse(path)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}