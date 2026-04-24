package br.com.inspec360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.inspec360.data.local.entity.StructureEntity

@Dao
interface StructureDao {
    @Insert
    suspend fun insert(structure: StructureEntity): Long

    @Update
    suspend fun update(structure: StructureEntity)

    @Delete
    suspend fun delete(structure: StructureEntity)

    @Query("SELECT * FROM structures WHERE id = :id")
    suspend fun getById(id: Long): StructureEntity?

    @Query("SELECT * FROM structures ORDER BY numero ASC")
    suspend fun getAll(): List<StructureEntity>

    @Query("SELECT * FROM structures WHERE numero LIKE :searchQuery OR tipo LIKE :searchQuery ORDER BY numero ASC")
    suspend fun search(searchQuery: String): List<StructureEntity>

    @Query("SELECT * FROM structures WHERE critica = 1 ORDER BY numero ASC")
    suspend fun getCritical(): List<StructureEntity>
}
