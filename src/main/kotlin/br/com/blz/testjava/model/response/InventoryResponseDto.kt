package br.com.blz.testjava.model.response

import br.com.blz.testjava.entity.WarehousesEntity

data class InventoryResponseDto(
  var warehouses: List<WarehousesEntity> = listOf()
) {
  var quantity: Long? = null
    set(_) {
      field = warehouses.sumOf { it.quantity }
    }
}
