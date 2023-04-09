package com.example.csi5175

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.csi5175.backend.model.Product

class productAdapter(private val myDataset: List<Product>,
                     private val listener: OnItemClickListener,

                     private val addClick: (Product) -> Unit,
                     private val plusClick: (Product) -> Unit,
                     private val minusClick: (Product) -> Unit,
                     private val shareClicks: (Product) -> Unit): RecyclerView.Adapter<productAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Product)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageView: ImageView = itemView.findViewById(R.id.product_image)
        val textView_product_name: TextView = itemView.findViewById(R.id.product_name)
        val textView_product_price: TextView = itemView.findViewById(R.id.product_price)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(myDataset[position])
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // myDataset[position].image?.planes
        // todo: image insert
        //holder.imageView.setImageDrawable(myDataset[position].image?.planes)
        holder.imageView.setOnClickListener { addClick(myDataset[position]) }
        holder.textView_product_name.text = myDataset[position].pname
        holder.textView_product_price.text = myDataset[position].price.toString()
    }

    override fun getItemCount() = myDataset.size
}

