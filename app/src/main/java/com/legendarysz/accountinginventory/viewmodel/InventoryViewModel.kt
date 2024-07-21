package com.legendarysz.accountinginventory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.legendarysz.accountinginventory.data.AppDatabase
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.models.Transactions
import com.legendarysz.accountinginventory.repository.InventoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//InventoryViewModel.kt
class InventoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InventoryRepository

    val allProducts: LiveData<List<Product>>
    val allTransactions: LiveData<List<Transactions>>

    init {
        val database = AppDatabase.getDatabase(application) // Get database instance
        val productDao = database.productDao()
        val transactionsDao = database.transactionsDao()
        repository = InventoryRepository(productDao, transactionsDao)
        allProducts = repository.allProducts
        allTransactions = repository.allTransactions
    }

    fun insertProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(product)
    }

    fun updateProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(product)
    }

    fun deleteProduct(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(product)
    }

    fun getProductById(id: Int): LiveData<Product?> {
        return liveData {
            val product = repository.getProductById(id)
            emit(product)
        }
    }

    fun insertTransaction(transaction: Transactions) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(transaction)
    }

    fun updateProductStock(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateProduct(product)
    }
}
