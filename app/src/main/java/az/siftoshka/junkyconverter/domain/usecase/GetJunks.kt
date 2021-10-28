package az.siftoshka.junkyconverter.domain.usecase

import az.siftoshka.junkyconverter.domain.model.Junk
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use-case to get all junks.
 */
class GetJunks @Inject constructor(
    private val repository: JunkRepository
) {

    operator fun invoke(): Flow<List<Junk>> {
        return repository.getAllJunks()
    }
}