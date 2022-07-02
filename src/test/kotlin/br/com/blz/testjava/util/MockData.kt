package br.com.blz.testjava.util

import br.com.blz.testjava.entity.InventoryEntity
import br.com.blz.testjava.entity.ProductEntity
import br.com.blz.testjava.entity.WarehousesEntity
import br.com.blz.testjava.enums.WarehouseTypeEnum
import br.com.blz.testjava.model.request.InventoryRequestDto
import br.com.blz.testjava.model.request.ProductRequestDto
import br.com.blz.testjava.model.request.WarehousesRequestDto
import br.com.blz.testjava.model.response.InventoryResponseDto
import br.com.blz.testjava.model.response.ProductResponseDto
import br.com.blz.testjava.model.response.WarehousesResponseDto

class MockData {
  companion object {
    val validProductRequest = ProductRequestDto(
      sku = 10,
      name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 535g",
      inventory = InventoryRequestDto(
        quantity = 10,
        warehouses = listOf(
          WarehousesRequestDto(
            locality = "SP",
            quantity = 10,
            type = WarehouseTypeEnum.ECOMMERCE
          )
        )
      )
    )

    val validProductResponse = ProductResponseDto(
      sku = 10,
      name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 535g",
      inventory = InventoryResponseDto(
        warehouses = listOf(
          WarehousesResponseDto(
            locality = "SP",
            quantity = 10,
            type = WarehouseTypeEnum.ECOMMERCE
          )
        )
      ).apply {
        quantity = 10
      }
    ).apply {
      isMarketable = true
    }

    val validProductEntity = ProductEntity(
      sku = 10,
      name = "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 535g",
      inventory = InventoryEntity(
        quantity = 10,
        warehouses = listOf(
          WarehousesEntity(
            locality = "SP",
            quantity = 10,
            type = WarehouseTypeEnum.ECOMMERCE
          )
        )
      )
    )
  }
}
