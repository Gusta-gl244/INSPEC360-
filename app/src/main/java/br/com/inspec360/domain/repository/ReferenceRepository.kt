package br.com.inspec360.domain.repository

import br.com.inspec360.domain.model.Reference

interface ReferenceRepository {
    suspend fun insert(reference: Reference): Long
    suspend fun delete(reference: Reference)
    suspend fun getById(id: Long): Reference?
    suspend fun getByCategory(category: String): List<Reference>
    suspend fun getAll(): List<Reference>
}
