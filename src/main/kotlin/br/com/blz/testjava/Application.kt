package br.com.blz.testjava

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BuyFoodApplication

fun main(args: Array<String>) {
  runApplication<BuyFoodApplication>(*args)
}
