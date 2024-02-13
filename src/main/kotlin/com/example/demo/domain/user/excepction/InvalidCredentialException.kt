package com.example.demo.domain.user.excepction

data class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()
