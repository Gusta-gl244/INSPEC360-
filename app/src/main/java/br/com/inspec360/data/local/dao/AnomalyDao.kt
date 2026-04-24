package br.com.inspec360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.inspec360.data.local.entity.AnomalyEntity

@Dao
interface AnomalyDao {
    @Insert
    suspend fun insert(anomaly: AnomalyEntity): Long

    @Update
    suspend fun update(anomaly: AnomalyEntity)

    @Delete
    suspend fun delete(anomaly: AnomalyEntity)

    @Query("SELECT * FROM anomalies WHERE id = :id")
    suspend fun getById(id: Long): AnomalyEntity?

    @Query("SELECT * FROM anomalies WHERE inspecaoId = :inspectionId ORDER BY id DESC")
    suspend fun getByInspectionId(inspectionId: Long): List<AnomalyEntity>

    @Query("SELECT * FROM anomalies WHERE severidade = :severity")
    suspend fun getBySeverity(severity: String): List<AnomalyEntity>

    @Query("SELECT * FROM anomalies ORDER BY id DESC")
    suspend fun getAll(): List<AnomalyEntity>
}
