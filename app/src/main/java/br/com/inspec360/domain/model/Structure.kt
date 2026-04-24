package br.com.inspec360.domain.model

data class Structure(
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
