package com.example.demo.domain.user.service

import com.example.demo.domain.user.dto.*

interface UserService {

    fun signUp(signUpRequest: SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse

    fun login(loginrequest: LoginRequest): LoginResponse
}