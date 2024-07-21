package com.legendarysz.accountinginventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.legendarysz.accountinginventory.databinding.FragmentAddProductBinding
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.viewmodel.InventoryViewModel

//AddProductFragment.kt
class AddProductFragment : Fragment() {

    private var _binding: FragmentAddProductBinding? = null
    private val binding get() = _binding!!

    private val inventoryViewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddProduct.setOnClickListener {
            val name = binding.editTextProductName.text.toString()
            val purchasingPrice = binding.editTextProductPurchasingPrice.text.toString().toDoubleOrNull()
            val sellingPrice = binding.editTextProductSellingPrice.text.toString().toDoubleOrNull()
            val stockQuantity = binding.editTextProductStockQuantity.text.toString().toIntOrNull()
            val unitOfMeasurement = binding.editTextProductUnit.text.toString()

            if (name.isEmpty() || purchasingPrice == null || sellingPrice == null || stockQuantity == null || unitOfMeasurement.isEmpty()) {
                Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            } else {
                val product = Product(
                    name = name,
                    purchasingPrice = purchasingPrice,
                    sellingPrice = sellingPrice,
                    stockQuantity = stockQuantity,
                    unitOfMeasurement = unitOfMeasurement
                )

                inventoryViewModel.insertProduct(product)
                Toast.makeText(context, "Product added", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
