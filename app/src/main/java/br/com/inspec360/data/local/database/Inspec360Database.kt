package br.com.inspec360.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.inspec360.data.local.dao.AnomalyDao
import br.com.inspec360.data.local.dao.InspectionDao
import br.com.inspec360.data.local.dao.ReferenceDao
import br.com.inspec360.data.local.dao.StructureDao
import br.com.inspec360.data.local.dao.UserDao
import br.com.inspec360.data.local.entity.AnomalyEntity
import br.com.inspec360.data.local.entity.InspectionEntity
import br.com.inspec360.data.local.entity.ReferenceEntity
import br.com.inspec360.data.local.entity.StructureEntity
import br.com.inspec360.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        StructureEntity::class,
        InspectionEntity::class,
        AnomalyEntity::class,
        ReferenceEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Inspec360Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun structureDao(): StructureDao
    abstract fun inspectionDao(): InspectionDao
    abstract fun anomalyDao(): AnomalyDao
    abstract fun referenceDao(): ReferenceDao
}
