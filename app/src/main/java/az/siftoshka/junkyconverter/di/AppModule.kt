package az.siftoshka.junkyconverter.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import az.siftoshka.junkyconverter.data.model.JunkDatabase
import az.siftoshka.junkyconverter.data.repository.JunkRepositoryImpl
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import az.siftoshka.junkyconverter.domain.usecases.GetJunks
import az.siftoshka.junkyconverter.domain.usecases.JunkUseCases
import az.siftoshka.junkyconverter.domain.usecases.UpdateJunk
import az.siftoshka.junkyconverter.domain.utils.Constants
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
    fun provideJunkDatabase(app: Application): JunkDatabase {
        return Room.databaseBuilder(app, JunkDatabase::class.java, Constants.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideJunkRepository(db: JunkDatabase): JunkRepository {
        return JunkRepositoryImpl(db.getJunkDAO())
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.applicationContext.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideJunkUseCases(repository: JunkRepository, app: Application): JunkUseCases {
        return JunkUseCases(
            getJunks = GetJunks(repository, app),
            updateJunk = UpdateJunk(repository)
        )
    }
}