package com.legendarysz.accountinginventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.legendarysz.accountinginventory.databinding.FragmentEditProductBinding
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.viewmodel.InventoryViewModel

//EditProductFragment.kt
class EditProductFragment : Fragment() {

    private var _binding: FragmentEditProductBinding? = null
    private val binding get() = _binding!!

    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: EditProductFragmentArgs by navArgs()
        val productId = args.productId

        inventoryViewModel.getProductById(productId).observe(viewLifecycleOwner) { product ->
            product?.let {
                binding.editProductName.setText(it.name)
                binding.editProductPurchasingPrice.setText(it.purchasingPrice.toString())
                binding.editProductSellingPrice.setText(it.sellingPrice.toString())
                binding.editProductStockQuantity.setText(it.stockQuantity.toString())
                binding.editProductUnit.setText(it.unitOfMeasurement)
            }
        }

        binding.buttonUpdateProduct.setOnClickListener {
            val updatedProduct = Product(
                id = productId,
                name = binding.editProductName.text.toString(),
                purchasingPrice = binding.editProductPurchasingPrice.text.toString().toDouble(),
                sellingPrice = binding.editProductSellingPrice.text.toString().toDouble(),
                stockQuantity = binding.editProductStockQuantity.text.toString().toInt(),
                unitOfMeasurement = binding.editProductUnit.text.toString()
            )
            inventoryViewModel.updateProduct(updatedProduct)
            findNavController().navigateUp()
        }

        binding.deleteButton.setOnClickListener {
            inventoryViewModel.getProductById(productId).observe(viewLifecycleOwner) { product ->
                product?.let {
                    inventoryViewModel.deleteProduct(it)
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
