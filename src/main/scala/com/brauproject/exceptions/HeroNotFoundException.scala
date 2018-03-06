package com.brauproject.exceptions

final case class HeroNotFoundException(private val message: String = "",
                                       private val cause: Throwable = None.orNull) extends Exception(message, cause)
