package com.legendarysz.accountinginventory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.legendarysz.accountinginventory.data.AppDatabase
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.repository.InventoryRepository

class InventoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: InventoryRepository

    val allProducts: LiveData<List<Product>>

    init {
        val database = AppDatabase.getDatabase(application) // Get database instance
        val productDao = database.productDao()
        val transactionsDao = database.transactionsDao()
        val customerDao = database.customerDao()
        val expenseDao = database.expenseDao()
        repository = InventoryRepository(productDao, transactionsDao, customerDao, expenseDao)
        allProducts = repository.allProducts
    }

    // ... rest of your ViewModel code
}