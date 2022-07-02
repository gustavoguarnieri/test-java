package br.com.blz.testjava.entity

data class InventoryEntity(
  var quantity: Long? = null,
  var warehouses: List<WarehousesEntity>? = listOf()
)
