package az.siftoshka.junkyconverter.domain.repository

import az.siftoshka.junkyconverter.domain.model.Junk
import kotlinx.coroutines.flow.Flow

/**
 * The repository class for [Junk] model.
 */
interface JunkRepository {

    suspend fun insertJunk(junk: Junk)

    suspend fun insertInitialJunks()

    fun getAllJunks(): Flow<List<Junk>>
}