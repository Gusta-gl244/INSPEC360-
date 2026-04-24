package br.com.inspec360.domain.repository

import br.com.inspec360.domain.model.Structure

interface StructureRepository {
    suspend fun insert(structure: Structure): Long
    suspend fun update(structure: Structure)
    suspend fun delete(structure: Structure)
    suspend fun getById(id: Long): Structure?
    suspend fun getAll(): List<Structure>
    suspend fun search(query: String): List<Structure>
    suspend fun getCritical(): List<Structure>
}
