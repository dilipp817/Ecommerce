package com.dev.smartpos.service

import com.dev.smartpos.domain.product.Product
import com.dev.smartpos.dto.product.CreateProductRequest
import com.dev.smartpos.dto.product.ProductResponse
import com.dev.smartpos.dto.product.UpdateProductRequest
import java.util.*

interface ProductService {
    fun getAllProducts(): MutableList<ProductResponse>
    fun getProductById(id: UUID): Product?
    fun saveProduct(request: CreateProductRequest): Product
    fun updateProduct(id: UUID, request: UpdateProductRequest): Product
    fun deleteProduct(id: UUID)
}