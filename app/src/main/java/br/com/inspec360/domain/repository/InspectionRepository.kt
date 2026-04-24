package br.com.inspec360.domain.repository

import br.com.inspec360.domain.model.Inspection

interface InspectionRepository {
    suspend fun insert(inspection: Inspection): Long
    suspend fun update(inspection: Inspection)
    suspend fun delete(inspection: Inspection)
    suspend fun getById(id: Long): Inspection?
    suspend fun getAll(): List<Inspection>
    suspend fun getByStructureId(structureId: Long): List<Inspection>
    suspend fun getByInspectorId(inspectorId: Long): List<Inspection>
    suspend fun getByStatus(status: String): List<Inspection>
    suspend fun getUnsynchronized(): List<Inspection>
}
