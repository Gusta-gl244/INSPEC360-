package br.com.inspec360.data.mapper

import br.com.inspec360.data.local.entity.StructureEntity
import br.com.inspec360.domain.model.Structure

fun StructureEntity.toDomain(): Structure = Structure(
    id = id,
    numero = numero,
    tipo = tipo,
    classe = classe,
    progressiva = progressiva,
    travessia = travessia,
    critica = critica,
    coordenadaX = coordenadaX,
    coordenadaY = coordenadaY
)

fun Structure.toEntity(): StructureEntity = StructureEntity(
    id = id,
    numero = numero,
    tipo = tipo,
    classe = classe,
    progressiva = progressiva,
    travessia = travessia,
    critica = critica,
    coordenadaX = coordenadaX,
    coordenadaY = coordenadaY
)
