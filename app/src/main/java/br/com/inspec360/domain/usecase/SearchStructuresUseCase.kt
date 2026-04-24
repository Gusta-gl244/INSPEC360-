package br.com.inspec360.domain.usecase

import br.com.inspec360.domain.model.Structure
import br.com.inspec360.domain.repository.StructureRepository
import javax.inject.Inject

class SearchStructuresUseCase @Inject constructor(
    private val structureRepository: StructureRepository
) {
    suspend operator fun invoke(query: String): Result<List<Structure>> {
        return try {
            val structures = structureRepository.search(query)
            Result.success(structures)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
