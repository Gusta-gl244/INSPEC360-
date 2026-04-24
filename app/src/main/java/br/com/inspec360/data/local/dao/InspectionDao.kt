package br.com.inspec360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.inspec360.data.local.entity.InspectionEntity

@Dao
interface InspectionDao {
    @Insert
    suspend fun insert(inspection: InspectionEntity): Long

    @Update
    suspend fun update(inspection: InspectionEntity)

    @Delete
    suspend fun delete(inspection: InspectionEntity)

    @Query("SELECT * FROM inspections WHERE id = :id")
    suspend fun getById(id: Long): InspectionEntity?

    @Query("SELECT * FROM inspections ORDER BY dataHora DESC")
    suspend fun getAll(): List<InspectionEntity>

    @Query("SELECT * FROM inspections WHERE estruturaId = :structureId ORDER BY dataHora DESC")
    suspend fun getByStructureId(structureId: Long): List<InspectionEntity>

    @Query("SELECT * FROM inspections WHERE inspetorId = :inspectorId ORDER BY dataHora DESC")
    suspend fun getByInspectorId(inspectorId: Long): List<InspectionEntity>

    @Query("SELECT * FROM inspections WHERE status = :status ORDER BY dataHora DESC")
    suspend fun getByStatus(status: String): List<InspectionEntity>

    @Query("SELECT * FROM inspections WHERE sincronizada = 0")
    suspend fun getUnsynchronized(): List<InspectionEntity>
}
