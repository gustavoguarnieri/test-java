package br.com.blz.testjava.controller

import br.com.blz.testjava.model.request.ProductRequestDto
import br.com.blz.testjava.model.response.ProductResponseDto
import br.com.blz.testjava.service.ProductService
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/v1/products")
@RestController
@Validated
class ProductController(private val productService: ProductService) {

  private val log = KotlinLogging.logger {}

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun save(@Valid @RequestBody productRequestDto: ProductRequestDto) {
    log.info { "save: creating product=${productRequestDto.name}" }
    return productService.save(productRequestDto).also {
      log.info { "save: created product=${productRequestDto.name}" }
    }
  }

  @GetMapping("/{sku}")
  fun get(@PathVariable sku: Long): ProductResponseDto {
    log.info { "get: getting product sku=$sku" }
    return productService.get(sku).also {
      log.info { "get: got product sku=$sku" }
    }
  }

  @PutMapping("/{sku}")
  fun update(@PathVariable sku: Long, @RequestBody productRequestDto: ProductRequestDto): ProductResponseDto {
    log.info { "update: updating product sku=$sku" }
    return productService.update(productRequestDto).also {
      log.info { "update: updated product sku=$sku" }
    }
  }

  @DeleteMapping("/{sku}")
  fun delete(@PathVariable sku: Long) {
    log.info { "delete: deleting product sku=$sku" }
    return productService.delete(sku).also {
      log.info { "delete: deleted product sku=$sku" }
    }
  }
}
