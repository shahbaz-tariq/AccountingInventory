package com.legendarysz.accountinginventory.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//Product.kt
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val purchasingPrice: Double,
    val sellingPrice: Double,
    val stockQuantity: Int,
    val unitOfMeasurement: String
)