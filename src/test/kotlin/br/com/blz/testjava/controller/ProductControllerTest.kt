package br.com.blz.testjava.controller

import br.com.blz.testjava.exception.NotFoundException
import br.com.blz.testjava.service.ProductService
import br.com.blz.testjava.util.MockData
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@WebMvcTest(ProductController::class)
class ProductControllerTest {

  @MockBean
  private lateinit var productService: ProductService

  @Autowired
  private lateinit var mockMvc: MockMvc

  @Autowired
  private lateinit var objectMapper: ObjectMapper

  @Test
  fun `GIVEN valid product WHEN call save method THEN save new product`() {
    mockMvc.perform(
      post(BASE_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(MockData.validProductRequest))
    )
      .andExpect(status().isCreated)
  }

  @Test
  fun `GIVEN a valid sku WHEN call get method THEN return the product data`() {
    Mockito.`when`(productService.get(MockData.validProductRequest.sku)).thenReturn(MockData.validProductResponse)

    mockMvc.perform(
      get("$BASE_PATH/{sku}", MockData.validProductRequest.sku)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(MockData.validProductRequest))
    )
      .andExpect(status().isOk)
      .andExpect(jsonPath("$.sku").value(MockData.validProductResponse.sku))
  }

  @Test
  fun `GIVEN a not found sku WHEN call get method THEN throw NotFoundException`() {
    Mockito.`when`(productService.get(MockData.validProductRequest.sku))
      .thenThrow(NotFoundException("Product not found"))

    mockMvc.perform(
      get("$BASE_PATH/{sku}", MockData.validProductRequest.sku)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(MockData.validProductRequest))
    )
      .andExpect(status().isNotFound)
  }

  @Test
  fun `GIVEN a valid product WHEN call update method THEN return the updated data`() {
    Mockito.`when`(productService.get(MockData.validProductRequest.sku)).thenReturn(MockData.validProductResponse)

    mockMvc.perform(
      put("$BASE_PATH/{sku}", MockData.validProductRequest.sku)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(MockData.validProductRequest))
    )
      .andExpect(status().isOk)
  }

  @Test
  fun `GIVEN a valid product WHEN call delete method THEN return the updated data`() {
    mockMvc.perform(
      put("$BASE_PATH/{sku}", MockData.validProductRequest.sku)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(MockData.validProductRequest))
    )
      .andExpect(status().isOk)
  }

  companion object {
    const val BASE_PATH = "/api/v1/products"
  }
}
