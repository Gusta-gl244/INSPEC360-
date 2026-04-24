package br.com.inspec360.di

import android.content.Context
import androidx.room.Room
import br.com.inspec360.data.local.database.Inspec360Database
import br.com.inspec360.data.repository.AnomalyRepositoryImpl
import br.com.inspec360.data.repository.InspectionRepositoryImpl
import br.com.inspec360.data.repository.ReferenceRepositoryImpl
import br.com.inspec360.data.repository.StructureRepositoryImpl
import br.com.inspec360.data.repository.UserRepositoryImpl
import br.com.inspec360.domain.repository.AnomalyRepository
import br.com.inspec360.domain.repository.InspectionRepository
import br.com.inspec360.domain.repository.ReferenceRepository
import br.com.inspec360.domain.repository.StructureRepository
import br.com.inspec360.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Inspec360Database {
        return Room.databaseBuilder(
            context,
            Inspec360Database::class.java,
            "inspec360_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserRepository(database: Inspec360Database): UserRepository =
        UserRepositoryImpl(database.userDao())

    @Singleton
    @Provides
    fun provideStructureRepository(database: Inspec360Database): StructureRepository =
        StructureRepositoryImpl(database.structureDao())

    @Singleton
    @Provides
    fun provideInspectionRepository(database: Inspec360Database): InspectionRepository =
        InspectionRepositoryImpl(database.inspectionDao())

    @Singleton
    @Provides
    fun provideAnomalyRepository(database: Inspec360Database): AnomalyRepository =
        AnomalyRepositoryImpl(database.anomalyDao())

    @Singleton
    @Provides
    fun provideReferenceRepository(database: Inspec360Database): ReferenceRepository =
        ReferenceRepositoryImpl(database.referenceDao())
}
