package az.siftoshka.junkyconverter.data

import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.data.model.JunkDAO
import javax.inject.Inject

/**
 * The repository class for [Junk] model.
 */
class JunkRepository @Inject constructor(private val junkDAO: JunkDAO) {

    suspend fun insertJunk(junk: Junk) = junkDAO.insertJunk(junk)

    suspend fun insertInitialJunks() {
        val junks = listOf(
            Junk(JunkFoodCategory.BURGER, R.string.app_name, icon = R.drawable.ic_launcher_foreground),
            Junk(JunkFoodCategory.PIZZA, R.string.app_name, icon = R.drawable.ic_launcher_foreground),
            Junk(JunkFoodCategory.KEBAB, R.string.app_name, icon = R.drawable.ic_launcher_foreground),
            Junk(JunkFoodCategory.CHIPS, R.string.app_name, icon = R.drawable.ic_launcher_foreground),
            Junk(JunkFoodCategory.DONUT, R.string.app_name, icon = R.drawable.ic_launcher_foreground),
            Junk(JunkFoodCategory.TACO, R.string.app_name, icon = R.drawable.ic_launcher_foreground),
            Junk(JunkFoodCategory.MUFFIN, R.string.app_name, icon = R.drawable.ic_launcher_foreground)
        )
        junkDAO.insertInitialJunks(junks)
    }

    suspend fun getSelectedJunk(id: Int) = junkDAO.getSelectedJunk(id)

    suspend fun getAllJunksByName() = junkDAO.getAllJunksByName()
}

enum class JunkFoodCategory(val value: Int) {
    BURGER(0),
    PIZZA(1),
    KEBAB(2),
    CHIPS(3),
    DONUT(4),
    TACO(5),
    MUFFIN(6)
}