package br.com.inspec360.domain.usecase

import br.com.inspec360.domain.model.Inspection
import br.com.inspec360.domain.model.InspectionStatus
import br.com.inspec360.domain.repository.InspectionRepository
import javax.inject.Inject

class FinalizeInspectionUseCase @Inject constructor(
    private val inspectionRepository: InspectionRepository
) {
    suspend operator fun invoke(inspectionId: Long): Result<Unit> {
        return try {
            val inspection = inspectionRepository.getById(inspectionId)
            if (inspection != null) {
                val updated = inspection.copy(
                    status = InspectionStatus.FINALIZADA,
                    sincronizada = false
                )
                inspectionRepository.update(updated)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Inspeção não encontrada"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
