package br.com.inspec360.domain.usecase

import br.com.inspec360.domain.repository.InspectionRepository
import javax.inject.Inject

class GetInspectionHistoryUseCase @Inject constructor(
    private val inspectionRepository: InspectionRepository
) {
    suspend operator fun invoke(inspectorId: Long) = 
        inspectionRepository.getByInspectorId(inspectorId)
}
