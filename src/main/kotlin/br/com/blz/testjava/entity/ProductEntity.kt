package br.com.blz.testjava.entity

data class ProductEntity(
  var sku: Long,
  var name: String? = null,
  var inventory: InventoryEntity? = null,
  var isMarketable: Boolean? = null
)
