package br.com.blz.testjava.model.response

import java.time.LocalDateTime

data class BaseException(
  val timestamp: LocalDateTime? = null,
  val status: Int = 0,
  val error: String? = null,
  val message: String? = null,
  val path: String? = null,
)
