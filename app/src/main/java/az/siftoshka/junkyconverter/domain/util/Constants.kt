package az.siftoshka.junkyconverter.domain.util

import az.siftoshka.junkyconverter.R

/**
 * The constant variables of the application.
 */
object Constants {

    // Database
    const val DATABASE_NAME = "junky_db_exp"

    // SharedPreferences
    const val PREFS_NAME = "app_prefs"

    // NumPad
    var numPadNumbers = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "❮")

    // Default Junk name resources
    val junkNameRes = mutableListOf(
        R.string.item_burgers,
        R.string.item_pizzas,
        R.string.item_kebabs,
        R.string.item_chips,
        R.string.item_donuts,
        R.string.item_tacos,
        R.string.item_muffins,
        R.string.item_fried_chicken,
        R.string.item_burritos,
        R.string.item_nuggets,
        R.string.item_candies,
        R.string.item_cookies,
        R.string.item_hot_dogs
    )

    // About me
    const val DEV_TELEGRAM = "https://t.me/siftoshka"
    const val DEV_GITHUB = "https://github.com/siftoshka"
    const val DEV_INSTAGRAM = "https://www.instagram.com/siftoshka"
}