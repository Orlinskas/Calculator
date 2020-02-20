package com.orlinskas.calculator.adapter

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orlinskas.calculator.R
import com.orlinskas.calculator.model.Product
import kotlinx.android.synthetic.main.row_product.view.*

class ProductAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onItemClickListener: OnProductClickListener? = null
    private var productList: ArrayList<Product> = arrayListOf()
    private val outValue = TypedValue()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        //context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)
    }

    fun setItems(list: List<Product>) {
        if(list.isNotEmpty()) {
            productList = ArrayList(list)
            notifyDataSetChanged()
        }
    }

    fun getItems(): ArrayList<Product> {
        return productList
    }

    fun getEmployee(position: Int): Product {
        return productList[position]
    }

    override fun getItemCount(): Int {
        return productList.size
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
                onItemClickListener?.onProductClick(productList[adapterPosition], adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = productList[position]
        val itemHolder = holder as ItemViewHolder

        itemHolder.apply {
            name.text = item.name
            //image.setImageResource(R.drawable.)
            countValue.text = item.count.toString()
            priceItem.text = item.price
            overPrice.text = item.sum
            articul.text = item.sku
        }
    }

    fun removeEmployee(position: Int) {
        notifyItemRemoved(position)
        productList.removeAt(position)
    }

    fun addEmployee(product: Product) {
        productList.add(product)
        notifyItemInserted(productList.lastIndex)
    }

    interface OnProductClickListener {
        fun onProductClick(listItem: Product, position: Int)
    }

    fun setOnItemClickListener(onProductClickListener: OnProductClickListener) {
        onItemClickListener = onProductClickListener
    }
}