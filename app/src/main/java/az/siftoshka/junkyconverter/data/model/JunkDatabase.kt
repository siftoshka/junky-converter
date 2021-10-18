package az.siftoshka.junkyconverter.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import az.siftoshka.junkyconverter.domain.model.Junk

/**
 * The abstract class of database.
 */
@Database(entities = [Junk::class], version = 1, exportSchema = false)
abstract class JunkDatabase : RoomDatabase() {

    abstract fun getJunkDAO(): JunkDAO
}