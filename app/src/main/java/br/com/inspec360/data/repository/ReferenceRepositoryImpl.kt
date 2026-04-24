package br.com.inspec360.data.repository

import br.com.inspec360.data.local.dao.ReferenceDao
import br.com.inspec360.data.mapper.toDomain
import br.com.inspec360.data.mapper.toEntity
import br.com.inspec360.domain.model.Reference
import br.com.inspec360.domain.repository.ReferenceRepository
import javax.inject.Inject

class ReferenceRepositoryImpl @Inject constructor(
    private val referenceDao: ReferenceDao
) : ReferenceRepository {
    override suspend fun insert(reference: Reference): Long =
        referenceDao.insert(reference.toEntity())

    override suspend fun delete(reference: Reference) =
        referenceDao.delete(reference.toEntity())

    override suspend fun getById(id: Long): Reference? =
        referenceDao.getById(id)?.toDomain()

    override suspend fun getByCategory(category: String): List<Reference> =
        referenceDao.getByCategory(category).map { it.toDomain() }

    override suspend fun getAll(): List<Reference> =
        referenceDao.getAll().map { it.toDomain() }
}
