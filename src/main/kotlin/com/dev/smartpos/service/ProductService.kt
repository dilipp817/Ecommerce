package com.dev.smartpos.service

import com.dev.smartpos.ProductDTO
import java.util.*

interface ProductService {
    fun getAllProducts(): MutableList<ProductDTO?>?
    fun getProductById(id: Long): Optional<ProductDTO?>?
    fun saveProduct(productDTO: ProductDTO): ProductDTO?
    fun updateProduct(id: Long, productDTO: ProductDTO?): ProductDTO?
    fun deleteProduct(id: Long)
}