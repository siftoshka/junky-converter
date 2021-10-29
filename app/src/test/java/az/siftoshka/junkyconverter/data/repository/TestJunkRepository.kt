package az.siftoshka.junkyconverter.data.repository

import az.siftoshka.junkyconverter.domain.model.Junk
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * The test(fake) repository class for [Junk] model.
 */
class TestJunkRepository : JunkRepository {

    private val junks = mutableListOf<Junk>()

    override suspend fun insertJunk(junk: Junk) {
        junks.add(junk)
    }

    override suspend fun insertInitialJunks(junkNames: List<String>) {
        TODO("Not for testing here")
    }

    override suspend fun getSelectedJunk(id: Int): Junk {
        return junks.find { it.id == id } ?: Junk(99, "Error", 1f, 0, 0)
    }

    override fun getAllJunks(): Flow<List<Junk>> {
        return flow { emit(junks) }
    }
}