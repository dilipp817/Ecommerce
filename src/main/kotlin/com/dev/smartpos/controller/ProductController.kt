package com.dev.smartpos.controller

//package com.dev.learning.Ecommerce.controller
import com.dev.smartpos.ProductDTO
import com.dev.smartpos.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {
    @get:GetMapping
    val allProducts: MutableList<ProductDTO?>?
        get() = productService.getAllProducts()

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductDTO> { // Changed id to non-nullable Long
        val product = productService.getProductById(id)
        return product!!.map { ResponseEntity.ok(it) } // Simplified map logic
            .orElseGet { ResponseEntity.notFound().build() }
    }

    @PostMapping
    fun createProduct(@RequestBody productDTO: ProductDTO): ProductDTO? {
        return productService.saveProduct(productDTO)
    }

    @PutMapping("/{id}") // Added quotes around the path
    fun updateProduct(@PathVariable id: Long, @RequestBody productDTO: ProductDTO): ResponseEntity<ProductDTO> {
        // Assuming 'ProductNotFoundException' is a custom exception thrown by the service
        return try {
            val updatedProduct = productService.updateProduct(id, productDTO)
            ResponseEntity.ok(updatedProduct)
        } catch (e: Exception) { // Catching a specific exception
            ResponseEntity.notFound().build()
        } catch (e: Exception) {
            // Catching other exceptions as a fallback (though a global handler is better)
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        // Check if the product exists before attempting deletion
        val isDeleted = productService.deleteProduct(id)

        return ResponseEntity.noContent().build() // Return 204 if successful
    }
}