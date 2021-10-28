package az.siftoshka.junkyconverter.domain.usecase

import az.siftoshka.junkyconverter.domain.model.Junk
import az.siftoshka.junkyconverter.domain.repository.JunkRepository
import javax.inject.Inject

/**
 * Use-case to update existence junk.
 */
class UpdateJunk @Inject constructor(
    private val repository: JunkRepository
) {

    suspend operator fun invoke(junk: Junk) {
        repository.insertJunk(junk)
    }
}