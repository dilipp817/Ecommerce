package com.dev.smartpos.dto.product

data class UpdateProductRequest(
    val name: String,
    val description: String,
    val price: Double
)