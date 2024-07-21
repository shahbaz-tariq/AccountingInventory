package com.legendarysz.accountinginventory.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.legendarysz.accountinginventory.models.Transactions

//TransactionsDao.kt
@Dao
interface TransactionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transactions)

    @Update
    suspend fun update(transaction: Transactions)

    @Delete
    suspend fun delete(transaction: Transactions)

    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): LiveData<List<Transactions>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Int): Transactions?
}
