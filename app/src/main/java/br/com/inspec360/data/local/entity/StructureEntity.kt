package br.com.inspec360.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "structures")
data class StructureEntity(
    @PrimaryKey
    val id: Long,
    val numero: String,
    val tipo: String,
    val classe: String,
    val progressiva: Double,
    val travessia: String?,
    val critica: Boolean,
    val coordenadaX: Double,
    val coordenadaY: Double
)
