package az.siftoshka.junkyconverter.data.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import az.siftoshka.junkyconverter.utils.EnumCategoryConverter

/**
 * The abstract class of database.
 */
@Database(entities = [Junk::class], version = 1, exportSchema = false)
@TypeConverters(EnumCategoryConverter::class)
abstract class JunkDatabase : RoomDatabase() {

    abstract fun getJunkDAO(): JunkDAO
}