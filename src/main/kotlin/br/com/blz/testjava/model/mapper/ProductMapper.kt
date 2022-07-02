package br.com.blz.testjava.model.mapper

import br.com.blz.testjava.entity.ProductEntity
import br.com.blz.testjava.model.request.ProductRequestDto
import br.com.blz.testjava.model.response.ProductResponseDto
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface ProductMapper {

  fun convertToDto(productEntity: ProductEntity?): ProductResponseDto

  @InheritInverseConfiguration
  fun convertToModel(productRequestDto: ProductRequestDto): ProductEntity
}
