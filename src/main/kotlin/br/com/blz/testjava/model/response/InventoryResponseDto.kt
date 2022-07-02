package br.com.blz.testjava.model.response

data class InventoryResponseDto(
  var warehouses: List<WarehousesResponseDto> = listOf()
) {
  var quantity: Long? = null
    set(_) {
      field = warehouses.sumOf { it.quantity }
    }
}
