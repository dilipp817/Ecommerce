package com.dev.smartpos.dto.product

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Double
)