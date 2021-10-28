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

    override suspend fun insertInitialJunks(junkNames: List<String>) {
        val junks = listOf(
            Junk(
                id = 0,
                name = junkNames[0],
                value = junkDAO.getSelectedJunk(0).value,
                icon = R.drawable.hamburger,
                iconDescription = R.string.img_desc_item_burgers
            ),
            Junk(
                id = 1,
                name = junkNames[1],
                value = junkDAO.getSelectedJunk(1).value,
                icon = R.drawable.pizza,
                iconDescription = R.string.img_desc_item_pizzas
            ),
            Junk(
                id = 2,
                name = junkNames[2],
                value = junkDAO.getSelectedJunk(2).value,
                icon = R.drawable.kebab,
                iconDescription = R.string.img_desc_item_kebabs
            ),
            Junk(
                id = 3,
                name = junkNames[3],
                value = junkDAO.getSelectedJunk(3).value,
                icon = R.drawable.chips,
                iconDescription = R.string.img_desc_item_chips
            ),
            Junk(
                id = 4,
                name = junkNames[4],
                value = junkDAO.getSelectedJunk(4).value,
                icon = R.drawable.doughnut,
                iconDescription = R.string.img_desc_item_donuts
            ),
            Junk(
                id = 5,
                name = junkNames[5],
                value = junkDAO.getSelectedJunk(5).value,
                icon = R.drawable.taco,
                iconDescription = R.string.img_desc_item_tacos
            ),
            Junk(
                id = 6,
                name = junkNames[6],
                value = junkDAO.getSelectedJunk(6).value,
                icon = R.drawable.muffin,
                iconDescription = R.string.img_desc_item_muffins
            ),
            Junk(
                id = 7,
                name = junkNames[7],
                value = junkDAO.getSelectedJunk(7).value,
                icon = R.drawable.pan_chicken,
                iconDescription = R.string.img_desc_item_fried_chicken
            ),
            Junk(
                id = 8,
                name = junkNames[8],
                value = junkDAO.getSelectedJunk(8).value,
                icon = R.drawable.burrito,
                iconDescription = R.string.img_desc_item_burritos
            ),
            Junk(
                id = 9,
                name = junkNames[9],
                value = junkDAO.getSelectedJunk(9).value,
                icon = R.drawable.chicken,
                iconDescription = R.string.img_desc_item_nuggets
            ),
            Junk(
                id = 10,
                name = junkNames[10],
                value = junkDAO.getSelectedJunk(10).value,
                icon = R.drawable.lollipop,
                iconDescription = R.string.img_desc_item_candies
            ),
            Junk(
                id = 11,
                name = junkNames[11],
                value = junkDAO.getSelectedJunk(11).value,
                icon = R.drawable.cookie,
                iconDescription = R.string.img_desc_item_cookies
            ),
            Junk(
                id = 12,
                name = junkNames[12],
                value = junkDAO.getSelectedJunk(12).value,
                icon = R.drawable.hotdog,
                iconDescription = R.string.img_desc_item_hot_dogs
            )
        )
        junkDAO.insertInitialJunks(junks)
    }

    override fun getAllJunks() = junkDAO.getAllJunks()
}