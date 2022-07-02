package br.com.blz.testjava.model.request

data class InventoryRequestDto(
  val quantity: Long,
  val warehouses: List<WarehousesRequestDto> = listOf()
)
