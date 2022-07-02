package br.com.blz.testjava.service

import br.com.blz.testjava.exception.NotFoundException
import br.com.blz.testjava.exception.UnprocessableException
import br.com.blz.testjava.model.mapper.ProductMapper
import br.com.blz.testjava.repository.ProductRepository
import br.com.blz.testjava.util.MockData.Companion.validProductResponse
import br.com.blz.testjava.util.MockData.Companion.validProductEntity
import br.com.blz.testjava.util.MockData.Companion.validProductRequest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class ProductServiceTest {

  private lateinit var productService: ProductService

  @MockK
  private lateinit var productRepository: ProductRepository

  @MockK
  private lateinit var productMapper: ProductMapper

  @BeforeEach
  fun setUp() {
    MockKAnnotations.init(this)
    productService = ProductService(productRepository, productMapper)
  }

  @Test
  fun `GIVEN an unsaved product WHEN call save method THEN save new product`() {
    every {
      productRepository.get(any())
    } returns null

    every {
      productMapper.convertToModel(any())
    } returns validProductEntity

    every {
      productRepository.save(any())
    } returns Unit

    productService.save(validProductRequest)

    verify(exactly = 1) {
      productRepository.get(any())
      productMapper.convertToModel(any())
      productRepository.save(any())
    }
  }

  @Test
  fun `GIVEN an already saved product WHEN call save method THEN throw UnprocessableException`() {
    every {
      productRepository.get(any())
    } returns validProductEntity

    assertThrows<UnprocessableException> {
      productService.save(validProductRequest)
    }

    verify(exactly = 1) {
      productRepository.get(any())
    }

    verify(exactly = 0) {
      productMapper.convertToModel(any())
      productRepository.save(any())
    }
  }

  @Test
  fun `GIVEN a valid sku WHEN call get method THEN return the product data`() {
    every {
      productRepository.get(any())
    } returns validProductEntity

    every {
      productMapper.convertToDto(any())
    } returns validProductResponse

    val result = productService.get(validProductRequest.sku)

    assertThat(validProductResponse).usingRecursiveComparison().isEqualTo(result)

    verify(exactly = 1) {
      productRepository.get(any())
      productMapper.convertToDto(any())
    }
  }

  @Test
  fun `GIVEN a not found sku WHEN call get method THEN throw NotFoundException`() {
    every {
      productRepository.get(any())
    } returns null

    every {
      productMapper.convertToDto(any())
    } returns validProductResponse

    assertThrows<NotFoundException> {
      productService.get(validProductRequest.sku)
    }

    verify(exactly = 1) {
      productRepository.get(any())
    }

    verify(exactly = 0) {
      productMapper.convertToDto(any())
    }
  }

  @Test
  fun `GIVEN a valid product WHEN call update method THEN return the updated data`() {
    every {
      productMapper.convertToModel(any())
    } returns validProductEntity

    every {
      productRepository.update(any())
    } returns validProductEntity

    every {
      productMapper.convertToDto(any())
    } returns validProductResponse

    val result = productService.update(validProductRequest)

    assertThat(validProductResponse).usingRecursiveComparison().isEqualTo(result)

    verify(exactly = 1) {
      productMapper.convertToModel(any())
      productRepository.update(any())
      productMapper.convertToDto(any())
    }
  }

  @Test
  fun `GIVEN a valid product WHEN call delete method THEN return the updated data`() {
    every {
      productRepository.delete(any())
    } returns Unit

    productService.delete(validProductRequest.sku)

    verify(exactly = 1) {
      productRepository.delete(any())
    }
  }
}
