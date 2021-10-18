package az.siftoshka.junkyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import az.siftoshka.junkyconverter.domain.utils.Constants

/**
 * The data class of the data element Junk food.
 */
@Entity(tableName = Constants.TABLE_NAME)
data class Junk(
    @PrimaryKey var id: Int,
    var name: Int,
    var value: Float? = 1f,
    var icon: Int,
    var iconDescription: Int
)