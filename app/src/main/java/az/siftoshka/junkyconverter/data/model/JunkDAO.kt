package az.siftoshka.junkyconverter.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.siftoshka.junkyconverter.domain.model.Junk
import az.siftoshka.junkyconverter.domain.utils.Constants
import kotlinx.coroutines.flow.Flow

/**
 * The DAO interface of the [JunkDatabase].
 */
@Dao
interface JunkDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJunk(junk: Junk)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInitialJunks(junks: List<Junk>)

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE id = :id")
    suspend fun getSelectedJunk(id: Int) : Junk

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAllJunks() : Flow<List<Junk>>
}