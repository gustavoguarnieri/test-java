package br.com.blz.testjava.model.request

import javax.validation.constraints.Positive

data class ProductRequestDto(
  @field:Positive(message = "required sku with positive number")
  val sku: Long,
  val name: String,
  val inventory: InventoryRequestDto?
)
