package br.com.inspec360.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Long,
    val username: String,
    val passwordHash: String,
    val role: String
)
