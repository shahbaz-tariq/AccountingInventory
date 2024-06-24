package com.legendarysz.accountinginventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.legendarysz.accountinginventory.databinding.ProductListItemBinding
import com.legendarysz.accountinginventory.models.Product

//ProductListAdapter.kt
class ProductListAdapter(private val onItemClick: (Product) -> Unit) : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(view, onItemClick, this)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class ProductViewHolder(itemView: View, private val onItemClick: (Product) -> Unit, private val adapter: ProductListAdapter) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val productStock: TextView = itemView.findViewById(R.id.productStock)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(adapter.getItem(position))
                }
            }
        }

        fun bind(product: Product) {
            productName.text = product.name
            productPrice.text = product.sellingPrice.toString()
            productStock.text = product.stockQuantity.toString()
        }
    }

    class ProductsComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}
