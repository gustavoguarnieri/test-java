package br.com.blz.testjava.service

import br.com.blz.testjava.exception.NotFoundException
import br.com.blz.testjava.exception.UnprocessableException
import br.com.blz.testjava.model.mapper.ProductMapper
import br.com.blz.testjava.model.request.ProductRequestDto
import br.com.blz.testjava.model.response.ProductResponseDto
import br.com.blz.testjava.repository.ProductRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ProductService(
  private val productRepository: ProductRepository,
  private val productMapper: ProductMapper
) {

  private val log = KotlinLogging.logger {}

  fun save(productRequestDto: ProductRequestDto) {
    productRepository.get(productRequestDto.sku)?.let {
      log.info { "save: product already created sku=${productRequestDto.sku}" }
      throw UnprocessableException("Product already created")
    } ?: run {
      val productEntity = productMapper.convertToModel(productRequestDto)
      productRepository.save(productEntity)
    }
  }

  fun get(sku: Long): ProductResponseDto {
    return productRepository.get(sku)?.let {
      productMapper.convertToDto(it)
    } ?: run {
      log.info { "get: Product not found sku=$sku" }
      throw NotFoundException("Product not found")
    }
  }

  fun update(productRequestDto: ProductRequestDto): ProductResponseDto {
    val productEntity = productMapper.convertToModel(productRequestDto)
    val updatedProductEntity = productRepository.update(productEntity)
    return productMapper.convertToDto(updatedProductEntity)
  }

  fun delete(sku: Long) {
    productRepository.delete(sku)
  }
}
