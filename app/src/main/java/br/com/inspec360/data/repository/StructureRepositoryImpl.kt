package br.com.inspec360.data.repository

import br.com.inspec360.data.local.dao.StructureDao
import br.com.inspec360.data.mapper.toDomain
import br.com.inspec360.data.mapper.toEntity
import br.com.inspec360.domain.model.Structure
import br.com.inspec360.domain.repository.StructureRepository
import javax.inject.Inject

class StructureRepositoryImpl @Inject constructor(
    private val structureDao: StructureDao
) : StructureRepository {
    override suspend fun insert(structure: Structure): Long =
        structureDao.insert(structure.toEntity())

    override suspend fun update(structure: Structure) =
        structureDao.update(structure.toEntity())

    override suspend fun delete(structure: Structure) =
        structureDao.delete(structure.toEntity())

    override suspend fun getById(id: Long): Structure? =
        structureDao.getById(id)?.toDomain()

    override suspend fun getAll(): List<Structure> =
        structureDao.getAll().map { it.toDomain() }

    override suspend fun search(query: String): List<Structure> =
        structureDao.search("%$query%").map { it.toDomain() }

    override suspend fun getCritical(): List<Structure> =
        structureDao.getCritical().map { it.toDomain() }
}
