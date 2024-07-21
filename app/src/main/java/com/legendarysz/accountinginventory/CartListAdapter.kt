package com.legendarysz.accountinginventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.legendarysz.accountinginventory.models.Product

//CartListAdapter.kt
class CartListAdapter(private val onQuantityChange: (Product, Int) -> Unit) : RecyclerView.Adapter<CartListAdapter.CartViewHolder>() {

    private val cartItems = mutableListOf<Pair<Product, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_list_item, parent, false)
        return CartViewHolder(view, onQuantityChange)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val (product, quantity) = cartItems[position]
        holder.bind(product, quantity)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    fun submitList(newCartItems: List<Pair<Product, Int>>) {
        cartItems.clear()
        cartItems.addAll(newCartItems)
        notifyDataSetChanged()
    }

    class CartViewHolder(itemView: View, private val onQuantityChange: (Product, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productQuantity: EditText = itemView.findViewById(R.id.productQuantity)
        private val updateButton: Button = itemView.findViewById(R.id.updateButton)
        private var currentProduct: Product? = null

        init {
            updateButton.setOnClickListener {
                val quantity = productQuantity.text.toString().toIntOrNull() ?: 0
                currentProduct?.let { product ->
                    onQuantityChange(product, quantity)
                }
            }
        }

        fun bind(product: Product, quantity: Int) {
            currentProduct = product
            productName.text = product.name
            productQuantity.setText(quantity.toString())
        }
    }
}
