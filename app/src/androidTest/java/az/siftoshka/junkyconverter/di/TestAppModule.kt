package az.siftoshka.junkyconverter.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import az.siftoshka.junkyconverter.data.model.JunkDatabase
import az.siftoshka.junkyconverter.data.repository.JunkRepositoryImpl
import az.siftoshka.junkyconverter.data.repository.LocalRepositoryImpl
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import az.siftoshka.junkyconverter.domain.repository.LocalRepository
import az.siftoshka.junkyconverter.domain.usecase.GetJunks
import az.siftoshka.junkyconverter.domain.usecase.JunkUseCases
import az.siftoshka.junkyconverter.domain.usecase.UpdateJunk
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Test DI Module of the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideJunkDatabase(app: Application): JunkDatabase {
        return Room.inMemoryDatabaseBuilder(app, JunkDatabase::class.java).build()
    }

    @Provides
    @Singleton
    fun provideJunkRepository(db: JunkDatabase): JunkRepository {
        return JunkRepositoryImpl(db.getJunkDAO())
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.applicationContext.getSharedPreferences("test_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideLocalRepository(app: Application, preferences: SharedPreferences): LocalRepository {
        return LocalRepositoryImpl(app, preferences)
    }

    @Provides
    @Singleton
    fun provideJunkUseCases(repository: JunkRepository): JunkUseCases {
        return JunkUseCases(
            getJunks = GetJunks(repository),
            updateJunk = UpdateJunk(repository)
        )
    }

}