package az.siftoshka.junkyconverter.di

import android.content.Context
import androidx.room.Room
import az.siftoshka.junkyconverter.common.Constants
import az.siftoshka.junkyconverter.data.model.JunkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The DI Module of the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideJunkDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, JunkDatabase::class.java, Constants.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideJunkDAO(database: JunkDatabase) = database.getJunkDAO()

}