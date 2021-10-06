package az.siftoshka.junkyconverter.utils

import androidx.room.TypeConverter
import az.siftoshka.junkyconverter.data.JunkFoodCategory

/**
 * The converter class for the [JunkFoodCategory].
 */
class EnumCategoryConverter {

    @TypeConverter
    fun toJunk(value: Int) = enumValues<JunkFoodCategory>()[value]

    @TypeConverter
    fun fromJunk(value: JunkFoodCategory) = value.ordinal
}