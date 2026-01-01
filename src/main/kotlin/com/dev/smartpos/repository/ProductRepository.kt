package com.dev.smartpos.repository

import com.dev.smartpos.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository: JpaRepository<ProductEntity, UUID> {
}