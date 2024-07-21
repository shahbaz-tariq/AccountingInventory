package com.legendarysz.accountinginventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.legendarysz.accountinginventory.databinding.FragmentNewTransactionBinding
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.models.Transactions
import com.legendarysz.accountinginventory.viewmodel.InventoryViewModel

//NewTransactionFragment.kt
class NewTransactionFragment : Fragment() {

    private var _binding: FragmentNewTransactionBinding? = null
    private val binding get() = _binding!!

    private val inventoryViewModel: InventoryViewModel by viewModels()

    private lateinit var productsAdapter: ProductListAdapter
    private lateinit var cartAdapter: CartListAdapter

    private val cart = mutableMapOf<Product, Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productsAdapter = ProductListAdapter { product ->
            addToCart(product)
        }

        cartAdapter = CartListAdapter { product, quantity ->
            updateCart(product, quantity)
        }

        binding.productsRecyclerView.adapter = productsAdapter
        binding.productsRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.cartRecyclerView.adapter = cartAdapter
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(context)

        inventoryViewModel.allProducts.observe(viewLifecycleOwner) { products ->
            productsAdapter.submitList(products)
        }

        binding.saveTransactionButton.setOnClickListener {
            saveTransaction()
        }
    }

    private fun addToCart(product: Product) {
        val currentQuantity = cart[product] ?: 0
        if (product.stockQuantity > currentQuantity) {
            cart[product] = currentQuantity + 1
            cartAdapter.submitList(cart.toList())
        } else {
            Toast.makeText(requireContext(), "Not enough stock", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateCart(product: Product, quantity: Int) {
        if (quantity > 0) {
            if (quantity <= product.stockQuantity) {
                cart[product] = quantity
                cartAdapter.submitList(cart.toList())
            } else {
                Toast.makeText(requireContext(), "Not enough stock", Toast.LENGTH_SHORT).show()
            }
        } else {
            cart.remove(product)
            cartAdapter.submitList(cart.toList())
        }
    }

    private fun saveTransaction() {
        val paymentType = binding.paymentTypeSpinner.selectedItem.toString()
        val payNow = binding.payNowSwitch.isChecked

        val transactions = cart.map { (product, quantity) ->
            Transactions(
                productId = product.id,
                productName = product.name,
                quantity = quantity,
                totalAmount = product.sellingPrice * quantity,
                date = System.currentTimeMillis(),
                paymentType = paymentType,
                payNow = payNow
            )
        }

        transactions.forEach { transaction ->
            val product = cart.keys.find { it.id == transaction.productId } ?: return@forEach
            inventoryViewModel.insertTransaction(transaction)
            inventoryViewModel.updateProduct(product.copy(stockQuantity = product.stockQuantity - transaction.quantity))
        }

        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}