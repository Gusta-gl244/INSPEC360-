package br.com.inspec360.data.mapper

import br.com.inspec360.data.local.entity.UserEntity
import br.com.inspec360.domain.model.User
import br.com.inspec360.domain.model.UserRole

fun UserEntity.toDomain(): User = User(
    id = id,
    username = username,
    passwordHash = passwordHash,
    role = UserRole.valueOf(role)
)

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    username = username,
    passwordHash = passwordHash,
    role = role.name
)
