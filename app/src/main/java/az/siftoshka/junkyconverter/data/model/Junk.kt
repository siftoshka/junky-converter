package az.siftoshka.junkyconverter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import az.siftoshka.junkyconverter.utils.Constants

/**
 * The data class of the data element Junk food.
 */
@Entity(tableName = Constants.TABLE_NAME)
data class Junk(
    var name: Int? = null,
    var value: Float? = 1f,
    var icon: Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}