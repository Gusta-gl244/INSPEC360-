package br.com.inspec360.domain.usecase

import br.com.inspec360.domain.model.Inspection
import br.com.inspec360.domain.model.InspectionStatus
import br.com.inspec360.domain.repository.InspectionRepository
import javax.inject.Inject

class CreateInspectionUseCase @Inject constructor(
    private val inspectionRepository: InspectionRepository
) {
    suspend operator fun invoke(
        estruturaId: Long,
        inspetorId: Long
    ): Result<Long> {
        return try {
            val inspection = Inspection(
                id = System.currentTimeMillis(),
                estruturaId = estruturaId,
                inspetorId = inspetorId,
                dataHora = System.currentTimeMillis(),
                status = InspectionStatus.EM_ANDAMENTO,
                sincronizada = false
            )
            val id = inspectionRepository.insert(inspection)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
