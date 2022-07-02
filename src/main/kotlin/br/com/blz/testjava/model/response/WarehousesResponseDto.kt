package br.com.blz.testjava.model.response

import br.com.blz.testjava.enums.WarehouseTypeEnum

data class WarehousesResponseDto(
  var locality: String,
  var quantity: Long,
  var type: WarehouseTypeEnum
)
