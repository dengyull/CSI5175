package com.example.csi5175

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.backend.model.Order

class OrderAdapter(private val myDataset: List<Order>) : RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.product_image)
        val textView_product_name: TextView = itemView.findViewById(R.id.product_name)
        val textView_product_price: TextView = itemView.findViewById(R.id.product_price)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            // todo:show product detail page


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // todo: image insert
        // holder.imageView.setImageBitmap(myDataset[position].image?.planes)
        holder.textView_product_price.text = myDataset[position].price.toString()
    }

    override fun getItemCount() = myDataset.size
}
