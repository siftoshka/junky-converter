package az.siftoshka.junkyconverter.screens.main.usecase

import az.siftoshka.junkyconverter.data.JunkRepository
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.utils.Resource
import az.siftoshka.junkyconverter.data.SharedPrefManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Use-case to get all junks.
 */
class GetJunkListUseCase @Inject constructor(
    private val prefs: SharedPrefManager,
    private val repository: JunkRepository
) {

    operator fun invoke(): Flow<Resource<List<Junk>>> = flow {
        try {
            emit(Resource.Loading())
            val junks = repository.getAllJunks()
            emit(Resource.Success(junks))
        } catch (e: Exception) {
            emit(Resource.Error<List<Junk>>(""))
        }
    }
}