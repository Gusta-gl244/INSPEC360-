package br.com.inspec360.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "references")
data class ReferenceEntity(
    @PrimaryKey
    val id: Long,
    val categoria: String,
    val valor: String
)
