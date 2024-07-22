package com.legendarysz.accountinginventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.legendarysz.accountinginventory.databinding.ItemReportBinding
import com.legendarysz.accountinginventory.models.Transactions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// ReportsListAdapter.kt
class ReportsListAdapter : ListAdapter<Transactions, ReportsListAdapter.ReportViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }

    class ReportViewHolder(private val binding: ItemReportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transactions) {
            binding.productNameTextView.text = transaction.productName
            binding.quantityTextView.text = "Quantity: ${transaction.quantity}"
            binding.totalAmountTextView.text = "Total Amount: ${transaction.totalAmount}"
            binding.dateTextView.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                Date(transaction.date)
            )
            binding.paymentTypeTextView.text = "Payment Type: ${transaction.paymentType}"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Transactions>() {
        override fun areItemsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Transactions, newItem: Transactions): Boolean {
            return oldItem == newItem
        }
    }
}
