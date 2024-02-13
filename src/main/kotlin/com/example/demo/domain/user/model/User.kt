package com.example.demo.domain.user.model

import com.example.demo.domain.user.dto.UserResponse
import jakarta.persistence.*
import org.springframework.data.jpa.domain.AbstractPersistable_.id

@Entity
@Table(name = "app_user")
class User(

    @Embedded
    @Column(name = "nickname")
    var profile: Profile,

    @Column(name = "email", nullable = false)
    val email: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,

    @Column(name = "password", nullable = false)
    var password: String,


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        nickname = profile.nickname,
        role = role.name
    )
}