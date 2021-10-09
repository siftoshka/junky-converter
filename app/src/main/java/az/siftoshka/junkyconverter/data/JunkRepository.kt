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
            Junk(1, R.string.item_burgers, icon = R.drawable.ic_launcher_foreground),
            Junk(2, R.string.item_pizzas, icon = R.drawable.ic_launcher_foreground),
            Junk(3, R.string.item_kebabs, icon = R.drawable.ic_launcher_foreground),
            Junk(4, R.string.item_chips, icon = R.drawable.ic_launcher_foreground),
            Junk(5, R.string.item_donuts, icon = R.drawable.ic_launcher_foreground),
            Junk(6, R.string.item_tacos, icon = R.drawable.ic_launcher_foreground),
            Junk(7, R.string.item_muffins, icon = R.drawable.ic_launcher_foreground)
        )
        junkDAO.insertInitialJunks(junks)
    }

    suspend fun getSelectedJunk(id: Int) = junkDAO.getSelectedJunk(id)

    suspend fun getAllJunksByName() = junkDAO.getAllJunksByName()
}