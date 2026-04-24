package br.com.inspec360.util

import br.com.inspec360.data.local.entity.ReferenceEntity
import br.com.inspec360.data.local.entity.StructureEntity
import br.com.inspec360.data.local.entity.UserEntity

object DataSeed {
    fun getSampleUsers(): List<UserEntity> {
        return listOf(
            UserEntity(
                id = 1,
                username = "inspetor1",
                passwordHash = SecurityUtil.hashPassword("senha123"),
                role = "INSPETOR"
            ),
            UserEntity(
                id = 2,
                username = "inspetor2",
                passwordHash = SecurityUtil.hashPassword("senha123"),
                role = "INSPETOR"
            ),
            UserEntity(
                id = 3,
                username = "admin",
                passwordHash = SecurityUtil.hashPassword("admin123"),
                role = "ADMIN"
            )
        )
    }

    fun getSampleStructures(): List<StructureEntity> {
        return listOf(
            StructureEntity(
                id = 1,
                numero = "0/1",
                tipo = "Torre",
                classe = "Classe A",
                progressiva = 0.0,
                travessia = null,
                critica = false,
                coordenadaX = -23.5505,
                coordenadaY = -46.6333
            ),
            StructureEntity(
                id = 2,
                numero = "1/2",
                tipo = "Torre",
                classe = "Classe B",
                progressiva = 2.5,
                travessia = null,
                critica = false,
                coordenadaX = -23.5520,
                coordenadaY = -46.6360
            ),
            StructureEntity(
                id = 3,
                numero = "2/3",
                tipo = "Suspensão",
                classe = "Classe A",
                progressiva = 5.0,
                travessia = "Rio Pinheiros",
                critica = true,
                coordenadaX = -23.5540,
                coordenadaY = -46.6390
            ),
            StructureEntity(
                id = 4,
                numero = "3/4",
                tipo = "Torre",
                classe = "Classe A",
                progressiva = 7.5,
                travessia = null,
                critica = false,
                coordenadaX = -23.5560,
                coordenadaY = -46.6410
            ),
            StructureEntity(
                id = 5,
                numero = "4/5",
                tipo = "Ancoragem",
                classe = "Classe B",
                progressiva = 10.0,
                travessia = null,
                critica = false,
                coordenadaX = -23.5580,
                coordenadaY = -46.6430
            )
        )
    }

    fun getSampleReferences(): List<ReferenceEntity> {
        return listOf(
            // Severidades
            ReferenceEntity(1, "SEVERIDADE", "LEVE"),
            ReferenceEntity(2, "SEVERIDADE", "MODERADA"),
            ReferenceEntity(3, "SEVERIDADE", "GRAVE"),
            ReferenceEntity(4, "SEVERIDADE", "CRITICA"),

            // Fases
            ReferenceEntity(5, "FASE", "FASE_A"),
            ReferenceEntity(6, "FASE", "FASE_B"),
            ReferenceEntity(7, "FASE", "FASE_C"),

            // Tipos de Anomalia
            ReferenceEntity(8, "TIPO_ANOMALIA", "Trinca"),
            ReferenceEntity(9, "TIPO_ANOMALIA", "Sujidade"),
            ReferenceEntity(10, "TIPO_ANOMALIA", "Corrosão"),
            ReferenceEntity(11, "TIPO_ANOMALIA", "Deformação"),
            ReferenceEntity(12, "TIPO_ANOMALIA", "Emenda"),

            // Componentes
            ReferenceEntity(13, "COMPONENTE", "Isolador"),
            ReferenceEntity(14, "COMPONENTE", "Condutor"),
            ReferenceEntity(15, "COMPONENTE", "Cabo Pára-raio"),
            ReferenceEntity(16, "COMPONENTE", "Estrutura"),
            ReferenceEntity(17, "COMPONENTE", "Fundação"),
            ReferenceEntity(18, "COMPONENTE", "Hardware")
        )
    }
}
