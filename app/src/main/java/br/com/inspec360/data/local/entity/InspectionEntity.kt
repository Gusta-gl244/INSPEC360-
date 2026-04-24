package br.com.inspec360.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "inspections",
    foreignKeys = [
        ForeignKey(
            entity = StructureEntity::class,
            parentColumns = ["id"],
            childColumns = ["estruturaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["inspetorId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class InspectionEntity(
    @PrimaryKey
    val id: Long,
    val estruturaId: Long,
    val inspetorId: Long,
    val dataHora: Long,
    val status: String,
    val sincronizada: Boolean
)
