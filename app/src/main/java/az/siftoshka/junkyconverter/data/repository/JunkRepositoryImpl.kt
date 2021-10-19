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
            Junk(1, R.string.item_burgers, icon = R.drawable.hamburger, iconDescription = R.string.img_desc_item_burgers),
            Junk(2, R.string.item_pizzas, icon = R.drawable.pizza, iconDescription = R.string.img_desc_item_pizzas),
            Junk(3, R.string.item_kebabs, icon = R.drawable.kebab, iconDescription = R.string.img_desc_item_kebabs),
            Junk(4, R.string.item_chips, icon = R.drawable.chips, iconDescription = R.string.img_desc_item_chips),
            Junk(5, R.string.item_donuts, icon = R.drawable.doughnut, iconDescription = R.string.img_desc_item_donuts),
            Junk(6, R.string.item_tacos, icon = R.drawable.taco, iconDescription = R.string.img_desc_item_tacos),
            Junk(7, R.string.item_muffins, icon = R.drawable.muffin, iconDescription = R.string.img_desc_item_muffins),
            Junk(8, R.string.item_fried_chicken, icon = R.drawable.pan_chicken, iconDescription = R.string.img_desc_item_fried_chicken),
            Junk(9, R.string.item_burritos, icon = R.drawable.burrito, iconDescription = R.string.img_desc_item_burritos),
            Junk(10, R.string.item_nuggets, icon = R.drawable.chicken, iconDescription = R.string.img_desc_item_nuggets),
            Junk(11, R.string.item_candies, icon = R.drawable.lollipop, iconDescription = R.string.img_desc_item_candies),
            Junk(12, R.string.item_cookies, icon = R.drawable.cookie, iconDescription = R.string.img_desc_item_cookies),
            Junk(13, R.string.item_hot_dogs, icon = R.drawable.hotdog, iconDescription = R.string.img_desc_item_hot_dogs)
        )
        junkDAO.insertInitialJunks(junks)
    }

    override fun getAllJunks() = junkDAO.getAllJunks()
}