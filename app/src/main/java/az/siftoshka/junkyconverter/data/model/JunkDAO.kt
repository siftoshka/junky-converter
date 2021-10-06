package az.siftoshka.junkyconverter.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.siftoshka.junkyconverter.common.Constants
import kotlinx.coroutines.flow.Flow

/**
 * The DAO interface of the Junk Database.
 */
@Dao
interface JunkDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJunk(junk: Junk)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInitialJunks(junks: List<Junk>)

    @Query("SELECT * FROM ${Constants.TABLE_NAME} ORDER BY name ASC")
    fun getAllJunksByName() : Flow<List<Junk>>
}