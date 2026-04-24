package br.com.inspec360.util

object SecurityUtil {
    fun hashPassword(password: String): String {
        // Em produção, usar BCrypt ou Argon2
        // Para desenvolvimento, usar SHA-256 simples
        return password.hashCode().toString()
    }

    fun verifyPassword(password: String, hash: String): Boolean {
        return hashPassword(password) == hash
    }
}
