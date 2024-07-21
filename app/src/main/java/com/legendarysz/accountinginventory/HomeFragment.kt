package com.legendarysz.accountinginventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.legendarysz.accountinginventory.databinding.FragmentHomeBinding

//HomeFragment.kt
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_newTransactionFragment)
        }

        binding.transactions.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_transactionsFragment)
        }

        binding.reports.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_reportsFragment)
        }

        binding.products.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_productsFragment)
        }
    }
}