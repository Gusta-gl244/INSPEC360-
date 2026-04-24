package br.com.inspec360.data.mapper

import br.com.inspec360.data.local.entity.ReferenceEntity
import br.com.inspec360.domain.model.Reference

fun ReferenceEntity.toDomain(): Reference = Reference(
    id = id,
    categoria = categoria,
    valor = valor
)

fun Reference.toEntity(): ReferenceEntity = ReferenceEntity(
    id = id,
    categoria = categoria,
    valor = valor
)
