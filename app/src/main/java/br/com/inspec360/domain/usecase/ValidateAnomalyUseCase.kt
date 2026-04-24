package br.com.inspec360.domain.usecase

import br.com.inspec360.domain.model.Anomaly
import br.com.inspec360.domain.model.Severity
import br.com.inspec360.domain.repository.AnomalyRepository
import javax.inject.Inject

class ValidateAnomalyUseCase @Inject constructor(
    private val anomalyRepository: AnomalyRepository
) {
    suspend operator fun invoke(anomaly: Anomaly): Result<Unit> {
        return try {
            // Validação: Severidade GRAVE ou CRÍTICA exige foto
            if ((anomaly.severidade == Severity.GRAVE || anomaly.severidade == Severity.CRITICA) &&
                anomaly.fotoPath.isNullOrEmpty()
            ) {
                return Result.failure(Exception("Foto obrigatória para severidade grave ou crítica"))
            }

            // GPS obrigatório
            if (anomaly.latitude == 0.0 || anomaly.longitude == 0.0) {
                return Result.failure(Exception("Localização GPS obrigatória"))
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
