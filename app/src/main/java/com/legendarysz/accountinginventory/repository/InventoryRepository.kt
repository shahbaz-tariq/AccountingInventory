package com.legendarysz.accountinginventory.repository

import androidx.lifecycle.LiveData
import com.legendarysz.accountinginventory.data.ProductDao
import com.legendarysz.accountinginventory.data.TransactionsDao
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.models.Transactions

//InventoryRepository.kt
class InventoryRepository(
    private val productDao: ProductDao,
    private val transactionDao: TransactionsDao,
) {

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    val allTransactions: LiveData<List<Transactions>> = transactionDao.getAllTransactions()

    suspend fun insert(product: Product) {
        productDao.insert(product)
    }

    suspend fun update(product: Product) {
        productDao.update(product)
    }

    suspend fun delete(product: Product) {
        productDao.delete(product)
    }

    suspend fun getProductById(id: Int): Product? {
        return productDao.getProductById(id)
    }

    suspend fun insert(transaction: Transactions) {
        transactionDao.insert(transaction)
    }
}
