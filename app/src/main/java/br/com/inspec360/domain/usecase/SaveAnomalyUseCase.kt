package br.com.inspec360.domain.usecase

import br.com.inspec360.domain.model.Anomaly
import br.com.inspec360.domain.repository.AnomalyRepository
import javax.inject.Inject

class SaveAnomalyUseCase @Inject constructor(
    private val anomalyRepository: AnomalyRepository,
    private val validateAnomalyUseCase: ValidateAnomalyUseCase
) {
    suspend operator fun invoke(anomaly: Anomaly): Result<Long> {
        return try {
            // Valida antes de salvar
            validateAnomalyUseCase(anomaly).getOrThrow()

            val id = anomalyRepository.insert(anomaly)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
