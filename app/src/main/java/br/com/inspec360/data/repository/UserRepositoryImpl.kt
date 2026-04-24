package br.com.inspec360.data.repository

import br.com.inspec360.data.local.dao.UserDao
import br.com.inspec360.data.mapper.toDomain
import br.com.inspec360.data.mapper.toEntity
import br.com.inspec360.domain.model.User
import br.com.inspec360.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun insert(user: User): Long =
        userDao.insert(user.toEntity())

    override suspend fun update(user: User) =
        userDao.update(user.toEntity())

    override suspend fun delete(user: User) =
        userDao.delete(user.toEntity())

    override suspend fun getById(id: Long): User? =
        userDao.getById(id)?.toDomain()

    override suspend fun getByUsername(username: String): User? =
        userDao.getByUsername(username)?.toDomain()

    override suspend fun getAll(): List<User> =
        userDao.getAll().map { it.toDomain() }
}
