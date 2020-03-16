package com.orlinskas.calculator.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.orlinskas.calculator.R
import com.orlinskas.calculator.data.model.Product
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import kotlinx.android.synthetic.main.row_product.view.*

class ProductAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onItemClickListener: OnProductClickListener? = null
    private var productResponseList: ArrayList<Product> = arrayListOf()
    private val outValue = TypedValue()
    private lateinit var transformation: Transformation

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        //context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)

        transformation = RoundedTransformationBuilder()
            .oval(true)
            .build()
    }

    fun setItems(list: List<Product>) {
        if(list.isNotEmpty()) {
            productResponseList = ArrayList(list)
            notifyDataSetChanged()
        }
    }

    fun getItems(): ArrayList<Product> {
        return productResponseList
    }

    fun getEmployee(position: Int): Product {
        return productResponseList[position]
    }

    override fun getItemCount(): Int {
        return productResponseList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)

        return ItemViewHolder(inflater.inflate(R.layout.row_product, parent,false))
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.product_image
        val name: TextView = itemView.name
        val countValue: TextView = itemView.count_value
        val priceItem: TextView = itemView.price_count
        val overPrice: TextView = itemView.over_price
        val articul: TextView = itemView.articul
        init {
            itemView.setOnClickListener {
                onItemClickListener?.onProductClick(productResponseList[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = productResponseList[position]
        val itemHolder = holder as ItemViewHolder

        itemHolder.apply {
            name.text = item.name
            Picasso.with(context)
                .load(item.image).fit().transform(transformation)
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(image)

            countValue.text = item.count.toString()
            priceItem.text = item.price
            overPrice.text = item.sum
            articul.text = item.sku
        }
    }

    fun removeEmployee(position: Int) {
        notifyItemRemoved(position)
        productResponseList.removeAt(position)
    }

    fun addEmployee(productResponse: Product) {
        productResponseList.add(productResponse)
        notifyItemInserted(productResponseList.lastIndex)
    }

    interface OnProductClickListener {
        fun onProductClick(listItem: Product, position: Int)
    }

    fun setOnItemClickListener(onProductClickListener: OnProductClickListener) {
        onItemClickListener = onProductClickListener
    }
}