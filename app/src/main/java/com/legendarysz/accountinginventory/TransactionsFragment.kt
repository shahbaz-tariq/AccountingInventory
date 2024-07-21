package com.legendarysz.accountinginventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.legendarysz.accountinginventory.databinding.FragmentTransactionsBinding
import com.legendarysz.accountinginventory.viewmodel.InventoryViewModel

// TransactionsFragment.kt
class TransactionsFragment : Fragment() {

    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!

    private val inventoryViewModel: InventoryViewModel by viewModels()
    private lateinit var transactionListAdapter: TransactionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transactionListAdapter = TransactionListAdapter()
        binding.transactionsRecyclerView.adapter = transactionListAdapter
        binding.transactionsRecyclerView.layoutManager = LinearLayoutManager(context)

        inventoryViewModel.allTransactions.observe(viewLifecycleOwner) { transactions ->
            transactionListAdapter.submitList(transactions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
