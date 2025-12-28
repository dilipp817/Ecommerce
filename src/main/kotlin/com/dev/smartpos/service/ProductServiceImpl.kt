package com.dev.smartpos.service

import com.dev.smartpos.Product
import com.dev.smartpos.ProductDTO
import com.dev.smartpos.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun getAllProducts(): MutableList<ProductDTO?>? {
        return productRepository.findAll().stream()
            .map<ProductDTO?> { product: Product? -> this.convertToDTO(product!!) }
            .collect(Collectors.toList())
    }


    override fun getProductById(id: Long): Optional<ProductDTO?>? {
        return productRepository.findById(id)
            .map<ProductDTO?>(Function { product: Product? -> this.convertToDTO(product!!) })

    }

    override fun saveProduct(productDTO: ProductDTO): ProductDTO? {
        val product = convertToEntity(productDTO)
        val savedProduct = productRepository.save(product)
        return convertToDTO(savedProduct)
    }

    override fun updateProduct(
        id: Long,
        productDTO: ProductDTO?
    ): ProductDTO? {
        val product = productRepository.findById(id).orElseThrow()
        productDTO?.let {
            product.name = productDTO.name
            product.description = productDTO.description
            product.price = productDTO.price
        }
        val updatedProduct = productRepository.save(product)

        return convertToDTO(updatedProduct)
    }

    override fun deleteProduct(id: Long) {
        productRepository.deleteById(id)
    }

    // Convert Product Entity to ProductDTO
    private fun convertToDTO(product: Product): ProductDTO {
        return ProductDTO(product.id, product.name, product.description, product.price)
    }

    // Convert ProductDTO to Product Entity
    private fun convertToEntity(productDTO: ProductDTO?): Product {
        val product = Product()
        productDTO?.let {
            product.name = productDTO.name
            product.description = productDTO.description
            product.price = productDTO.price
        }
        return product
    }
}