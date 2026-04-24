package br.com.inspec360.domain.model

enum class InspectionStatus {
    EM_ANDAMENTO,
    FINALIZADA
}

data class Inspection(
    val id: Long,
    val estruturaId: Long,
    val inspetorId: Long,
    val dataHora: Long,
    val status: InspectionStatus,
    val sincronizada: Boolean
)
