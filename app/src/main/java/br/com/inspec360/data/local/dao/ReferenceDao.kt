package br.com.inspec360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.inspec360.data.local.entity.ReferenceEntity

@Dao
interface ReferenceDao {
    @Insert
    suspend fun insert(reference: ReferenceEntity): Long

    @Delete
    suspend fun delete(reference: ReferenceEntity)

    @Query("SELECT * FROM references WHERE id = :id")
    suspend fun getById(id: Long): ReferenceEntity?

    @Query("SELECT * FROM references WHERE categoria = :category ORDER BY valor ASC")
    suspend fun getByCategory(category: String): List<ReferenceEntity>

    @Query("SELECT * FROM references ORDER BY categoria ASC, valor ASC")
    suspend fun getAll(): List<ReferenceEntity>
}
