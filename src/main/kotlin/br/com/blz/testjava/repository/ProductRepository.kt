package br.com.blz.testjava.repository

import br.com.blz.testjava.entity.ProductEntity

interface ProductRepository {
  fun save(productEntity: ProductEntity)
  fun get(productSku: Long): ProductEntity?
  fun update(productEntity: ProductEntity): ProductEntity?
  fun delete(productSku: Long)
}
