package br.com.inspec360.data.mapper

import br.com.inspec360.data.local.entity.InspectionEntity
import br.com.inspec360.domain.model.Inspection
import br.com.inspec360.domain.model.InspectionStatus

fun InspectionEntity.toDomain(): Inspection = Inspection(
    id = id,
    estruturaId = estruturaId,
    inspetorId = inspetorId,
    dataHora = dataHora,
    status = InspectionStatus.valueOf(status),
    sincronizada = sincronizada
)

fun Inspection.toEntity(): InspectionEntity = InspectionEntity(
    id = id,
    estruturaId = estruturaId,
    inspetorId = inspetorId,
    dataHora = dataHora,
    status = status.name,
    sincronizada = sincronizada
)
