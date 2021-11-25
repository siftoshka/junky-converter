package az.siftoshka.junkyconverter.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import az.siftoshka.junkyconverter.data.LocalRepositoryImpl
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import az.siftoshka.junkyconverter.domain.repository.LocalRepository
import az.siftoshka.junkyconverter.domain.usecase.GetJunks
import az.siftoshka.junkyconverter.domain.usecase.JunkUseCases
import az.siftoshka.junkyconverter.domain.usecase.UpdateJunk
import az.siftoshka.junkyconverter.domain.util.Constants
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The DI Module of the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAndroidDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = JunkRepository.schema,
            context = app,
            name = JunkRepository.dbName
        )
    }

    @Provides
    @Singleton
    fun provideRocketCache(sqlDriver: SqlDriver): JunkRepository {
        return JunkRepository.build(sqlDriver = sqlDriver)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.applicationContext.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)

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