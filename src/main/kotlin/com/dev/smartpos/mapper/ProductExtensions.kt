package com.dev.smartpos.mapper

import com.dev.smartpos.domain.product.Product
import com.dev.smartpos.dto.product.CreateProductRequest
import com.dev.smartpos.dto.product.ProductResponse
import com.dev.smartpos.entity.ProductEntity

fun CreateProductRequest.toEntity() =
    ProductEntity(
        name = name,
        description = description,
        price = price
    )

fun ProductEntity.toDomain(): Product =
    Product(
        id = id
            ?: throw IllegalStateException("ProductEntity ID cannot be null"),
        name = name,
        description = description,
        price = price
    )

fun Product.toResponse() =
    ProductResponse(
        id = id,
        name = name,
        description = description,
        price = price
    )