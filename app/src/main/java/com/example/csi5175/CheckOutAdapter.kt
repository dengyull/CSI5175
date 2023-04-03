package com.example.csi5175

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.backend.model.Product

class CheckOutAdapter(private val myDataset: List<Product>,
                      private val plusClick: (Product,Int) -> Unit,
                      private val minusClick: (Product,Int) -> Unit) : RecyclerView.Adapter<CheckOutAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.product_image)
        val PNumber: EditText = itemView.findViewById(R.id.productNumber2)
        val decrease: ImageView = itemView.findViewById(R.id.button_Decrease2)
        val increase: ImageView = itemView.findViewById(R.id.button_increase2)
        val textView_product_name: TextView = itemView.findViewById(R.id.product_name)
        val textView_product_price: TextView = itemView.findViewById(R.id.product_price)
        init {
            decrease.setOnClickListener(this)
            increase.setOnClickListener(this)

        }

        override fun onClick(view: View) {

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.checkout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        myDataset[position].image?.planes
        // todo: image insert
        // holder.imageView.setImageBitmap(myDataset[position].image?.planes)
        holder.increase.setOnClickListener {
            plusClick(myDataset[position],position)
            notifyDataSetChanged()
        }
        holder.decrease.setOnClickListener {
            if(holder.PNumber.text.toString().toInt()>0){


                minusClick(myDataset[position],position)
                notifyDataSetChanged()
            }

        }
        holder.PNumber.setText( myDataset[position].quantity.toString())
        holder.textView_product_name.text = myDataset[position].pname
        holder.textView_product_price.text = myDataset[position].price.toString()
    }


    override fun getItemCount() = myDataset.size
}