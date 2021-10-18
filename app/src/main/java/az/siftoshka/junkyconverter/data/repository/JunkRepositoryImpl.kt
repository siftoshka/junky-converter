package az.siftoshka.junkyconverter.data.repository

import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.data.model.JunkDAO
import az.siftoshka.junkyconverter.domain.model.Junk
import az.siftoshka.junkyconverter.domain.repository.JunkRepository

/**
 * The implementation of repository class for [Junk] model.
 */
class JunkRepositoryImpl(
    private val junkDAO: JunkDAO
) : JunkRepository {

    override suspend fun insertJunk(junk: Junk) = junkDAO.insertJunk(junk)

    override suspend fun insertInitialJunks() {
        val junks = listOf(
            Junk(1, R.string.item_burgers, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_burgers),
            Junk(2, R.string.item_pizzas, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_pizzas),
            Junk(3, R.string.item_kebabs, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_kebabs),
            Junk(4, R.string.item_chips, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_chips),
            Junk(5, R.string.item_donuts, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_donuts),
            Junk(6, R.string.item_tacos, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_tacos),
            Junk(7, R.string.item_muffins, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_muffins),
            Junk(8, R.string.item_fried_chickens, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_fried_chickens),
            Junk(9, R.string.item_nuggets, icon = R.drawable.ic_launcher_foreground, iconDescription = R.string.img_desc_item_nuggets)
        )
        junkDAO.insertInitialJunks(junks)
    }

    override fun getAllJunks() = junkDAO.getAllJunks()
}