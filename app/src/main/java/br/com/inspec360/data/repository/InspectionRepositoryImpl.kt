package br.com.inspec360.data.repository

import br.com.inspec360.data.local.dao.InspectionDao
import br.com.inspec360.data.mapper.toDomain
import br.com.inspec360.data.mapper.toEntity
import br.com.inspec360.domain.model.Inspection
import br.com.inspec360.domain.repository.InspectionRepository
import javax.inject.Inject

class InspectionRepositoryImpl @Inject constructor(
    private val inspectionDao: InspectionDao
) : InspectionRepository {
    override suspend fun insert(inspection: Inspection): Long =
        inspectionDao.insert(inspection.toEntity())

    override suspend fun update(inspection: Inspection) =
        inspectionDao.update(inspection.toEntity())

    override suspend fun delete(inspection: Inspection) =
        inspectionDao.delete(inspection.toEntity())

    override suspend fun getById(id: Long): Inspection? =
        inspectionDao.getById(id)?.toDomain()

    override suspend fun getAll(): List<Inspection> =
        inspectionDao.getAll().map { it.toDomain() }

    override suspend fun getByStructureId(structureId: Long): List<Inspection> =
        inspectionDao.getByStructureId(structureId).map { it.toDomain() }

    override suspend fun getByInspectorId(inspectorId: Long): List<Inspection> =
        inspectionDao.getByInspectorId(inspectorId).map { it.toDomain() }

    override suspend fun getByStatus(status: String): List<Inspection> =
        inspectionDao.getByStatus(status).map { it.toDomain() }

    override suspend fun getUnsynchronized(): List<Inspection> =
        inspectionDao.getUnsynchronized().map { it.toDomain() }
}
