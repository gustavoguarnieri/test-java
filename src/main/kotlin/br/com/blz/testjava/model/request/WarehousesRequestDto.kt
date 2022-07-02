package br.com.blz.testjava.model.request

import br.com.blz.testjava.enums.WarehouseTypeEnum

data class WarehousesRequestDto(
  val locality: String,
  val quantity: Long,
  val type: WarehouseTypeEnum
)
