package com.dev.smartpos.controller

//package com.dev.learning.Ecommerce.controller
import com.dev.smartpos.dto.product.CreateProductRequest
import com.dev.smartpos.dto.product.ProductResponse
import com.dev.smartpos.dto.product.UpdateProductRequest
import com.dev.smartpos.mapper.toResponse
import com.dev.smartpos.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {
    @GetMapping("")
    fun getAllProducts() : MutableList<ProductResponse> {
        return productService.getAllProducts()
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: UUID): ResponseEntity<ProductResponse> { // Changed id to non-nullable Long
        val product = productService.getProductById(id)?.toResponse()
        return if (product != null) {
            ResponseEntity.ok(product)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/create")
    fun createProduct(@RequestBody request: CreateProductRequest): ProductResponse {
        return productService.saveProduct(request).toResponse()
    }

    @PutMapping("update/{id}") // Added quotes around the path
    fun updateProduct(@PathVariable id: UUID, @RequestBody request: UpdateProductRequest): ResponseEntity<ProductResponse> {
        // Assuming 'ProductNotFoundException' is a custom exception thrown by the service
        return ResponseEntity.ok(productService
            .updateProduct(id, request)
            .toResponse(),
        )
    }

    @DeleteMapping("delete/{id}")
    fun deleteProduct(@PathVariable id: UUID): ResponseEntity<Void> {
        // Check if the product exists before attempting deletion
        val isDeleted = productService.deleteProduct(id)

        return ResponseEntity.noContent().build() // Return 204 if successful
    }
}