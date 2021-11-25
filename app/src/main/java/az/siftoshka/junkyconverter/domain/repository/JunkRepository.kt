package az.siftoshka.junkyconverter.domain.repository

import az.siftoshka.junkyconverter.Database
import az.siftoshka.junkyconverter.data.JunkRepositoryImpl
import az.siftoshka.junkyconverter.datasource.Junk
import az.siftoshka.junkyconverter.domain.util.Constants
import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.flow.Flow

/**
 * The repository class for [Junk] model.
 */
interface JunkRepository {

    suspend fun insertJunk(junk: Junk)

    suspend fun insertInitialJunks(junkNames: List<String>)

    suspend fun getSelectedJunk(id: Int): Float

    fun getAllJunks(): Flow<List<Junk>>

    companion object Factory {

        fun build(sqlDriver: SqlDriver): JunkRepository {
            return JunkRepositoryImpl(
                db = Database(driver = sqlDriver)
            )
        }

        val schema: SqlDriver.Schema = Database.Schema
        const val dbName: String = Constants.DATABASE_NAME
    }
}