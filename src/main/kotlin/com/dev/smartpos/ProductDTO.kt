package com.dev.smartpos

import java.util.UUID

@JvmRecord
data class ProductDTO(val id: UUID, val name: String, val description: String, val price: Double)
