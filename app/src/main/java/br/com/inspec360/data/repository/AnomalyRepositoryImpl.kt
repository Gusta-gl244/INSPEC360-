package br.com.inspec360.data.repository

import br.com.inspec360.data.local.dao.AnomalyDao
import br.com.inspec360.data.mapper.toDomain
import br.com.inspec360.data.mapper.toEntity
import br.com.inspec360.domain.model.Anomaly
import br.com.inspec360.domain.repository.AnomalyRepository
import javax.inject.Inject

class AnomalyRepositoryImpl @Inject constructor(
    private val anomalyDao: AnomalyDao
) : AnomalyRepository {
    override suspend fun insert(anomaly: Anomaly): Long =
        anomalyDao.insert(anomaly.toEntity())

    override suspend fun update(anomaly: Anomaly) =
        anomalyDao.update(anomaly.toEntity())

    override suspend fun delete(anomaly: Anomaly) =
        anomalyDao.delete(anomaly.toEntity())

    override suspend fun getById(id: Long): Anomaly? =
        anomalyDao.getById(id)?.toDomain()

    override suspend fun getByInspectionId(inspectionId: Long): List<Anomaly> =
        anomalyDao.getByInspectionId(inspectionId).map { it.toDomain() }

    override suspend fun getBySeverity(severity: String): List<Anomaly> =
        anomalyDao.getBySeverity(severity).map { it.toDomain() }

    override suspend fun getAll(): List<Anomaly> =
        anomalyDao.getAll().map { it.toDomain() }
}
