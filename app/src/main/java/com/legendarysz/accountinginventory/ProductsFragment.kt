package com.legendarysz.accountinginventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.legendarysz.accountinginventory.databinding.FragmentProductsBinding
import com.legendarysz.accountinginventory.viewmodel.InventoryViewModel

//ProductsFragment.kt
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductListAdapter { product ->
            val action = ProductsFragmentDirections.actionProductsFragmentToEditProductFragment(product.id)
            findNavController().navigate(action)
        }

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        inventoryViewModel.allProducts.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_addProductFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
