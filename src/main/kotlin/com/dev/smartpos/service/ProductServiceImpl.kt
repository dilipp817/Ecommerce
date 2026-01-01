package com.dev.smartpos.service

import com.dev.smartpos.entity.ProductEntity
import com.dev.smartpos.domain.product.Product
import com.dev.smartpos.dto.product.CreateProductRequest
import com.dev.smartpos.dto.product.ProductResponse
import com.dev.smartpos.dto.product.UpdateProductRequest
import com.dev.smartpos.exceptions.ResourceNotFoundException
import com.dev.smartpos.mapper.toDomain
import com.dev.smartpos.mapper.toEntity
import com.dev.smartpos.mapper.toResponse
import com.dev.smartpos.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun getAllProducts(): MutableList<ProductResponse> {
        return productRepository.findAll()
            .map { it.toDomain() }
            .map { it.toResponse() }.toMutableList()
    }


    override fun getProductById(id: UUID): Product? {
        val entity = productRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException("Product not found with id: $id")
            }

        return entity.toDomain()

    }

    override fun saveProduct(request: CreateProductRequest): Product {
        val entity = request.toEntity()
        val savedEntity = productRepository.save(entity)
        return savedEntity.toDomain()
    }

    override fun updateProduct(id: UUID, request: UpdateProductRequest): Product {
        val entity = productRepository.findById(id)
            .orElseThrow {
                ResourceNotFoundException("Product not found with id: $id")
            }

        // Update mutable fields
        entity.name = request.name
        entity.description = request.description
        entity.price = request.price

        val updatedEntity = productRepository.save(entity)
        return updatedEntity.toDomain()
    }

    override fun deleteProduct(id: UUID) {
        productRepository.deleteById(id)
    }

    // Convert ProductEntity Entity to ProductDTO
//    private fun convertToDTO(productEntity: ProductEntity): ProductDTO {
//        return productEntity.let {
//            ProductDTO(
//                id = it.id,
//                name = it.name,
//                description = it.description,
//                price = it.price
//            )
//        }
//    }

//    // Convert ProductDTO to ProductEntity Entity
//    private fun convertToEntity(productDTO: ProductDTO): ProductEntity {
//        return productDTO.let {
//            ProductEntity(
//                id = UUID.randomUUID(),
//                name = it.name,
//                description = it.description,
//                price = it.price
//            )
//        }
//    }
}