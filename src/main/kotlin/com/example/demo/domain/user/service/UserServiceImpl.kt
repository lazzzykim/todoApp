package com.example.demo.domain.user.service

import com.example.demo.domain.exception.ModelNotFoundException
import com.example.demo.domain.user.dto.*
import com.example.demo.domain.user.excepction.InvalidCredentialException
import com.example.demo.domain.user.model.Profile
import com.example.demo.domain.user.model.User
import com.example.demo.domain.user.model.UserRole
import com.example.demo.domain.user.model.toResponse
import com.example.demo.domain.user.repository.UserRepository
import com.example.demo.infra.security.jwt.JwtPlugin
import jakarta.transaction.TransactionScoped
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.math.sign

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {

    override fun login(loginrequest: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(loginrequest.email)
            ?: throw ModelNotFoundException("User", null)

        if (user.role.name != loginrequest.role || !passwordEncoder.matches(loginrequest.password, user.password)) {
            throw InvalidCredentialException()
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    @Transactional
    override fun signUp(signUpRequest: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = signUpRequest.email,
                password = passwordEncoder.encode(signUpRequest.password),
                profile = Profile(
                    nickname = signUpRequest.nickname
                ),
                role = when (signUpRequest.role) {
                    UserRole.MEMBER.name -> UserRole.MEMBER
                    UserRole.ADMIN.name -> UserRole.ADMIN
                    else -> throw IllegalStateException("Invalid role")
                }
            )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(
        userId: Long,
        updateUserProfileRequest: UpdateUserProfileRequest
    ): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = updateUserProfileRequest.nickname
        )

        return userRepository.save(user).toResponse()
    }


}