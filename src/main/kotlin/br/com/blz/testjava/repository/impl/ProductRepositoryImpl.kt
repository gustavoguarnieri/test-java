package br.com.blz.testjava.repository.impl

import br.com.blz.testjava.entity.ProductEntity
import br.com.blz.testjava.repository.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl : ProductRepository {

  private var products: HashMap<Long, ProductEntity> = HashMap()

  override fun save(productEntity: ProductEntity) {
    products[productEntity.sku] = productEntity
  }

  override fun get(productSku: Long): ProductEntity? {
    return products[productSku]
  }

  override fun update(productEntity: ProductEntity): ProductEntity? {
    products[productEntity.sku] = productEntity
    return products[productEntity.sku]
  }

  override fun delete(productSku: Long) {
    products.remove(productSku)
  }
}
