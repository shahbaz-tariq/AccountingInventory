package com.legendarysz.accountinginventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.legendarysz.accountinginventory.databinding.FragmentReportsBinding
import com.legendarysz.accountinginventory.viewmodel.InventoryViewModel
import kotlinx.coroutines.launch

// ReportsFragment.kt
class ReportsFragment : Fragment() {

    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!

    private val inventoryViewModel: InventoryViewModel by viewModels()

    private lateinit var reportsAdapter: ReportsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reportsAdapter = ReportsListAdapter()
        binding.reportsRecyclerView.adapter = reportsAdapter
        binding.reportsRecyclerView.layoutManager = LinearLayoutManager(context)

        inventoryViewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            reportsAdapter.submitList(transactions)

            // Calculate total sales, total quantity sold, and total profit
            var totalSales = 0.0
            var totalQuantity = 0
            var totalProfit = 0.0

            viewLifecycleOwner.lifecycleScope.launch {
                for (transaction in transactions) {
                    val product = inventoryViewModel.getProductByIdSync(transaction.productId)
                    if (product != null) {
                        totalSales += transaction.totalAmount
                        totalQuantity += transaction.quantity
                        totalProfit += transaction.totalAmount - (product.purchasingPrice * transaction.quantity)
                    }
                }

                // Update the UI with calculated values
                binding.totalSalesTextView.text = "Total Sales: $totalSales"
                binding.totalQuantityTextView.text = "Total Quantity Sold: $totalQuantity"
                binding.totalProfitTextView.text = "Profit: $totalProfit"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
