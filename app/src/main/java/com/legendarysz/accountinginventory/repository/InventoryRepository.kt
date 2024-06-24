package com.legendarysz.accountinginventory.repository

import androidx.lifecycle.LiveData
import com.legendarysz.accountinginventory.data.CustomerDao
import com.legendarysz.accountinginventory.data.ExpenseDao
import com.legendarysz.accountinginventory.data.ProductDao
import com.legendarysz.accountinginventory.data.TransactionsDao
import com.legendarysz.accountinginventory.models.Customer
import com.legendarysz.accountinginventory.models.Expense
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.models.Transactions

//InventoryRepository.kt
class InventoryRepository(private val productDao: ProductDao, private val transactionDao: TransactionsDao, private val customerDao: CustomerDao, private val expenseDao: ExpenseDao) {

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    val allTransactions: LiveData<List<Transactions>> = transactionDao.getAllTransactions()
    val allCustomers: LiveData<List<Customer>> = customerDao.getAllCustomers()
    val allExpenses: LiveData<List<Expense>> = expenseDao.getAllExpenses()

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

}