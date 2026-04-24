package br.com.inspec360.domain.repository

import br.com.inspec360.domain.model.Anomaly

interface AnomalyRepository {
    suspend fun insert(anomaly: Anomaly): Long
    suspend fun update(anomaly: Anomaly)
    suspend fun delete(anomaly: Anomaly)
    suspend fun getById(id: Long): Anomaly?
    suspend fun getByInspectionId(inspectionId: Long): List<Anomaly>
    suspend fun getBySeverity(severity: String): List<Anomaly>
    suspend fun getAll(): List<Anomaly>
}
