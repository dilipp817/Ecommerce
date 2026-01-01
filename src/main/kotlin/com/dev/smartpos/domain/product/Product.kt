package com.dev.smartpos.domain.product

import java.util.UUID

data class Product(
    val id: UUID,
    val name: String,
    val description: String,
    val price: Double
)