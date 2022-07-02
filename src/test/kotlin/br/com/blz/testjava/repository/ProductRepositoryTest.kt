package br.com.blz.testjava.repository

import br.com.blz.testjava.repository.impl.ProductRepositoryImpl
import br.com.blz.testjava.util.MockData
import io.mockk.MockKAnnotations
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class ProductRepositoryTest {

  private lateinit var productRepository: ProductRepository

  @BeforeEach
  fun setUp() {
    MockKAnnotations.init(this)
    productRepository = ProductRepositoryImpl()
    productRepository.save(MockData.validProductEntity)
  }

  @Test
  fun `GIVEN valid product WHEN call save method THEN save new product`() {
    val product = productRepository.get(MockData.validProductRequest.sku)
    Assertions.assertThat(product).isNotNull
  }

  @Test
  fun `GIVEN valid product WHEN call get method THEN return the product data`() {
    val result = productRepository.get(MockData.validProductRequest.sku)
    Assertions.assertThat(MockData.validProductEntity).usingRecursiveComparison().isEqualTo(result)
  }

  @Test
  fun `GIVEN invalid sku product WHEN call get method THEN return no data`() {
    val result = productRepository.get(INVALID_SKU_PRODUCT)
    Assertions.assertThat(result).isNull()
  }

  @Test
  fun `GIVEN valid product WHEN call update method THEN return the updated data`() {
    val result = productRepository.update(MockData.validProductEntity)
    Assertions.assertThat(MockData.validProductEntity).usingRecursiveComparison().isEqualTo(result)
  }

  @Test
  fun `GIVEN a valid product WHEN call delete method THEN return the updated data`() {
    productRepository.delete(MockData.validProductRequest.sku)
    Assertions.assertThat(productRepository.get(MockData.validProductRequest.sku)).isNull()
  }


  companion object {
    const val INVALID_SKU_PRODUCT = 99999L
  }
}
