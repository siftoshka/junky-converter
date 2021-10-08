package az.siftoshka.junkyconverter.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.siftoshka.junkyconverter.utils.Constants

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

    @Query("SELECT * FROM ${Constants.TABLE_NAME} ORDER BY name ASC")
    suspend fun getAllJunksByName() : List<Junk>
}