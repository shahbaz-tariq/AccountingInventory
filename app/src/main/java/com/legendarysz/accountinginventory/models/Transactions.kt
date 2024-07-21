package com.legendarysz.accountinginventory.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//Transactions.kt
@Entity(tableName = "transactions")
data class Transactions(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val totalAmount: Double,
    val date: Long,
    val paymentType: String,  // cash or bank
    val payNow: Boolean       // pay now or pay later
)
