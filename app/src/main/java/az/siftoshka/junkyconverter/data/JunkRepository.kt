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
            Junk(1, R.string.item_burgers, 5f, R.drawable.ic_launcher_foreground, R.string.img_desc_item_burgers),
            Junk(2, R.string.item_pizzas, 8f, R.drawable.ic_launcher_foreground, R.string.img_desc_item_burgers),
            Junk(3, R.string.item_kebabs, 3f, R.drawable.ic_launcher_foreground, R.string.img_desc_item_burgers),
            Junk(4, R.string.item_chips, 1.5f, R.drawable.ic_launcher_foreground, R.string.img_desc_item_burgers),
            Junk(5, R.string.item_donuts, 1f, R.drawable.ic_launcher_foreground, R.string.img_desc_item_burgers),
            Junk(6, R.string.item_tacos, 2.5f, R.drawable.ic_launcher_foreground, R.string.img_desc_item_burgers),
            Junk(7, R.string.item_muffins, 1.2f, R.drawable.ic_launcher_foreground, R.string.img_desc_item_burgers)
        )
        junkDAO.insertInitialJunks(junks)
    }

    suspend fun getSelectedJunk(id: Int) = junkDAO.getSelectedJunk(id)

    suspend fun getAllJunksByName() = junkDAO.getAllJunksByName()
}