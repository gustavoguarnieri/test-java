package br.com.blz.testjava.entity

import br.com.blz.testjava.enum.WarehouseTypeEnum

data class WarehousesEntity(
  var locality: String? = null,
  var quantity: Long = 0,
  var type: WarehouseTypeEnum
)
