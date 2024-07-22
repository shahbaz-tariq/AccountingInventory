package com.legendarysz.accountinginventory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.legendarysz.accountinginventory.databinding.ItemTransactionBinding
import com.legendarysz.accountinginventory.models.Transactions
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// TransactionListAdapter.kt
class TransactionListAdapter :
    ListAdapter<Transactions, TransactionListAdapter.TransactionViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }

    class TransactionViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transactions) {
            binding.apply {
                productIdTextView.text = transaction.productId.toString()
                productNameTextView.text = transaction.productName
                quantityTextView.text = transaction.quantity.toString()
                totalAmountTextView.text = transaction.totalAmount.toString()
                dateTextView.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                    Date(transaction.date)
                )
                paymentTypeTextView.text = transaction.paymentType
                payNowTextView.text = if (transaction.payNow) "Yes" else "No"
            }
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
