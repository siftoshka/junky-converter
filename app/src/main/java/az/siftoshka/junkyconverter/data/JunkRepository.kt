package az.siftoshka.junkyconverter.data

import az.siftoshka.junkyconverter.R
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.data.model.JunkDAO
import javax.inject.Inject

/**
 * The repository class for Junk food.
 */
class JunkRepository @Inject constructor(private val junkDAO: JunkDAO) {

    suspend fun insertJunk(junk: Junk) = junkDAO.insertJunk(junk)

    suspend fun insertInitialJunks() {
        val junks = listOf(
            Junk(R.string.app_name, icon = R.drawable.ic_launcher_background ),
            Junk(R.string.app_name, icon = R.drawable.ic_launcher_background ),
            Junk(R.string.app_name, icon = R.drawable.ic_launcher_background ),
            Junk(R.string.app_name, icon = R.drawable.ic_launcher_background ),
            Junk(R.string.app_name, icon = R.drawable.ic_launcher_background )
        )
        junkDAO.insertInitialJunks(junks)
    }

    fun getAllJunksByName() = junkDAO.getAllJunksByName()

}