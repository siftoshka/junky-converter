package az.siftoshka.junkyconverter.screens.junksetting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.usecases.GetJunkListUseCase
import az.siftoshka.junkyconverter.usecases.UpdateJunkUseCase
import az.siftoshka.junkyconverter.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * The [ViewModel] of the [JunkSettingsScreen].
 */
@HiltViewModel
class JunkSettingsViewModel @Inject constructor(
    private val getJunkListUseCase: GetJunkListUseCase,
    private val updateJunkUseCase: UpdateJunkUseCase
) : ViewModel() {

    private val _junksState = mutableStateOf(JunkListState())
    val junksState: State<JunkListState> = _junksState

    var junkItem: Junk? = null

    init {
        getAllJunks()
    }

    private fun getAllJunks() {
        getJunkListUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _junksState.value = JunkListState(junks = result.data)
                }
                is Resource.Error -> {
                    _junksState.value = JunkListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _junksState.value = JunkListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateJunk(junk: Junk, value: Float?) {
        if (value != null && value > 0) {
            val updatedJunk = Junk(junk.id, junk.name, value, junk.icon, junk.iconDescription)
            junkItem = updatedJunk
            updateJunkUseCase(updatedJunk).launchIn(viewModelScope)
        }
    }
}

data class JunkListState(
    val isLoading: Boolean = false,
    val junks: List<Junk>? = null,
    val error: String = String()
)