package br.com.inspec360.domain.usecase

import br.com.inspec360.domain.model.User
import br.com.inspec360.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, passwordHash: String): Result<User> {
        return try {
            val user = userRepository.getByUsername(username)
            if (user != null && user.passwordHash == passwordHash) {
                Result.success(user)
            } else {
                Result.failure(Exception("Credenciais inválidas"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
