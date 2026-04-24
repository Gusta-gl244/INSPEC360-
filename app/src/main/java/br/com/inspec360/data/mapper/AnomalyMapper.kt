package br.com.inspec360.data.mapper

import br.com.inspec360.data.local.entity.AnomalyEntity
import br.com.inspec360.domain.model.Anomaly
import br.com.inspec360.domain.model.OperationalRisk
import br.com.inspec360.domain.model.Phase
import br.com.inspec360.domain.model.SafetyRisk
import br.com.inspec360.domain.model.Severity

fun AnomalyEntity.toDomain(): Anomaly = Anomaly(
    id = id,
    inspecaoId = inspecaoId,
    componente = componente,
    tipoAnomalia = tipoAnomalia,
    fase = Phase.valueOf(fase),
    emenda = emenda,
    severidade = Severity.valueOf(severidade),
    riscoSeguranca = SafetyRisk.valueOf(riscoSeguranca),
    riscoOperacional = OperationalRisk.valueOf(riscoOperacional),
    observacao = observacao,
    fotoPath = fotoPath,
    latitude = latitude,
    longitude = longitude
)

fun Anomaly.toEntity(): AnomalyEntity = AnomalyEntity(
    id = id,
    inspecaoId = inspecaoId,
    componente = componente,
    tipoAnomalia = tipoAnomalia,
    fase = fase.name,
    emenda = emenda,
    severidade = severidade.name,
    riscoSeguranca = riscoSeguranca.name,
    riscoOperacional = riscoOperacional.name,
    observacao = observacao,
    fotoPath = fotoPath,
    latitude = latitude,
    longitude = longitude
)
