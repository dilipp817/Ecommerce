package com.dev.smartpos.dto.product

import java.util.UUID

data class ProductResponse(
    val id: UUID,
    val name: String,
    val description: String,
    val price: Double
)