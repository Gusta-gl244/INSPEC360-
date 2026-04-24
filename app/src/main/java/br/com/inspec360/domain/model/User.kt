package br.com.inspec360.domain.model

enum class UserRole {
    ADMIN,
    INSPETOR
}

data class User(
    val id: Long,
    val username: String,
    val passwordHash: String,
    val role: UserRole
)
