package com.dev.smartpos.repository

import com.dev.smartpos.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}