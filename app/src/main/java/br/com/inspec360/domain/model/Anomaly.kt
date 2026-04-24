package br.com.inspec360.domain.model

enum class Severity {
    LEVE,
    MODERADA,
    GRAVE,
    CRITICA
}

enum class Phase {
    FASE_A,
    FASE_B,
    FASE_C
}

enum class SafetyRisk {
    BAIXO,
    MEDIO,
    ALTO,
    CRITICO
}

enum class OperationalRisk {
    BAIXO,
    MEDIO,
    ALTO,
    CRITICO
}

data class Anomaly(
    val id: Long,
    val inspecaoId: Long,
    val componente: String,
    val tipoAnomalia: String,
    val fase: Phase,
    val emenda: Boolean,
    val severidade: Severity,
    val riscoSeguranca: SafetyRisk,
    val riscoOperacional: OperationalRisk,
    val observacao: String,
    val fotoPath: String?,
    val latitude: Double,
    val longitude: Double
)
