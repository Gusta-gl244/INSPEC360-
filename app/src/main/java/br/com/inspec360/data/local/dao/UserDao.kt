package br.com.inspec360.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.inspec360.data.local.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity): Long

    @Update
    suspend fun update(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getById(id: Long): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getByUsername(username: String): UserEntity?

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<UserEntity>
}
