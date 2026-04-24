package br.com.inspec360.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "anomalies",
    foreignKeys = [
        ForeignKey(
            entity = InspectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["inspecaoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AnomalyEntity(
    @PrimaryKey
    val id: Long,
    val inspecaoId: Long,
    val componente: String,
    val tipoAnomalia: String,
    val fase: String,
    val emenda: Boolean,
    val severidade: String,
    val riscoSeguranca: String,
    val riscoOperacional: String,
    val observacao: String,
    val fotoPath: String?,
    val latitude: Double,
    val longitude: Double
)
