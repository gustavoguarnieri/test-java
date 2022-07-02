package br.com.blz.testjava.model.response

data class ProductResponseDto(
  var sku: Long? = null,
  var name: String? = null,
  var inventory: InventoryResponseDto? = null
) {
  var isMarketable: Boolean? = null
    set(_) {
      field = inventory?.quantity?.let { it > 0 }
    }
}
