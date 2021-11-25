package az.siftoshka.junkyconverter.data

import az.siftoshka.junkyconverter.Database
import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.datasource.Junk
import az.siftoshka.junkyconverter.datasource.JunkQueries
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 * The implementation of repository class for [Junk] model.
 */
class JunkRepositoryImpl(
    db : Database
) : JunkRepository {

    private val queries: JunkQueries = db.junkQueries

    override suspend fun insertJunk(junk: Junk) = withContext(Dispatchers.IO) {
        queries.insertJunk(
            id = junk.id,
            name = junk.name,
            value = junk.value,
            icon = junk.icon,
            icon_description = junk.icon_description
        )
    }

    override suspend fun insertInitialJunks(junkNames: List<String>) = withContext(Dispatchers.IO) {
        val junks = listOf(
            Junk(
                id = 0,
                name = junkNames[0],
                value = getSelectedJunk(0),
                icon = R.drawable.hamburger,
                icon_description = R.string.img_desc_item_burgers
            ),
            Junk(
                id = 1,
                name = junkNames[1],
                value = getSelectedJunk(1),
                icon = R.drawable.pizza,
                icon_description = R.string.img_desc_item_pizzas
            ),
            Junk(
                id = 2,
                name = junkNames[2],
                value = getSelectedJunk(2),
                icon = R.drawable.kebab,
                icon_description = R.string.img_desc_item_kebabs
            ),
            Junk(
                id = 3,
                name = junkNames[3],
                value = getSelectedJunk(3),
                icon = R.drawable.chips,
                icon_description = R.string.img_desc_item_chips
            ),
            Junk(
                id = 4,
                name = junkNames[4],
                value = getSelectedJunk(4),
                icon = R.drawable.doughnut,
                icon_description = R.string.img_desc_item_donuts
            ),
            Junk(
                id = 5,
                name = junkNames[5],
                value = getSelectedJunk(5),
                icon = R.drawable.taco,
                icon_description = R.string.img_desc_item_tacos
            ),
            Junk(
                id = 6,
                name = junkNames[6],
                value = getSelectedJunk(6),
                icon = R.drawable.muffin,
                icon_description = R.string.img_desc_item_muffins
            ),
            Junk(
                id = 7,
                name = junkNames[7],
                value = getSelectedJunk(7),
                icon = R.drawable.pan_chicken,
                icon_description = R.string.img_desc_item_fried_chicken
            ),
            Junk(
                id = 8,
                name = junkNames[8],
                value = getSelectedJunk(8),
                icon = R.drawable.burrito,
                icon_description = R.string.img_desc_item_burritos
            ),
            Junk(
                id = 9,
                name = junkNames[9],
                value = getSelectedJunk(9),
                icon = R.drawable.chicken,
                icon_description = R.string.img_desc_item_nuggets
            ),
            Junk(
                id = 10,
                name = junkNames[10],
                value = getSelectedJunk(10),
                icon = R.drawable.lollipop,
                icon_description = R.string.img_desc_item_candies
            ),
            Junk(
                id = 11,
                name = junkNames[11],
                value = getSelectedJunk(11),
                icon = R.drawable.cookie,
                icon_description = R.string.img_desc_item_cookies
            ),
            Junk(
                id = 12,
                name = junkNames[12],
                value = getSelectedJunk(12),
                icon = R.drawable.hotdog,
                icon_description = R.string.img_desc_item_hot_dogs
            )
        )
        junks.forEach {
            try {
                insertJunk(junk = it)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun getSelectedJunk(id: Int): Float = withContext(Dispatchers.IO) {
        queries.getSelectedJunk(id).executeAsOneOrNull()?.value ?: 1f
    }

    override fun getAllJunks() : Flow<List<Junk>> {
        return queries.getAllJunks().asFlow().mapToList()
    }
}