package az.siftoshka.junkyconverter.screens.main.usecase

import az.siftoshka.junkyconverter.data.JunkRepository
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.utils.Resource
import az.siftoshka.junkyconverter.utils.SharedPrefManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use-case to get selected junk.
 */
class GetJunkUseCase @Inject constructor(
    private val prefs: SharedPrefManager,
    private val repository: JunkRepository
) {

    operator fun invoke(): Flow<Resource<Junk>> = flow {
        try {
            emit(Resource.Loading())
            val junk = repository.getSelectedJunk(prefs.getSelectedJunk())
            emit(Resource.Success(junk))
        } catch (e: Exception) {
            emit(Resource.Error<Junk>(""))
        }
    }
}