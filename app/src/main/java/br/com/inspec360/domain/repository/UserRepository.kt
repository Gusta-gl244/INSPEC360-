package br.com.inspec360.domain.repository

import br.com.inspec360.domain.model.User

interface UserRepository {
    suspend fun insert(user: User): Long
    suspend fun update(user: User)
    suspend fun delete(user: User)
    suspend fun getById(id: Long): User?
    suspend fun getByUsername(username: String): User?
    suspend fun getAll(): List<User>
}
