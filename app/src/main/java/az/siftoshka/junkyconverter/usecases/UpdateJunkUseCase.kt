package az.siftoshka.junkyconverter.usecases

import az.siftoshka.junkyconverter.data.JunkRepository
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.utils.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use-case to update existence junk.
 */
class UpdateJunkUseCase @Inject constructor(
    private val repository: JunkRepository
) {

    operator fun invoke(junk: Junk) = flow {
        try {
            emit(Resource.Loading())
            val junks = repository.insertJunk(junk)
            emit(Resource.Success(junks))
        } catch (e: Exception) {
            emit(Resource.Error<Junk>(""))
        }
    }
}