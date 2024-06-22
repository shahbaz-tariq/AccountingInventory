package com.legendarysz.accountinginventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.legendarysz.accountinginventory.models.Customer
import com.legendarysz.accountinginventory.models.Expense
import com.legendarysz.accountinginventory.models.Product
import com.legendarysz.accountinginventory.models.Transactions

//AppDatabase.kt
@Database(entities = [Product::class, Transactions::class, Customer::class, Expense::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun transactionsDao(): TransactionsDao
    abstract fun customerDao(): CustomerDao
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inventory_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}