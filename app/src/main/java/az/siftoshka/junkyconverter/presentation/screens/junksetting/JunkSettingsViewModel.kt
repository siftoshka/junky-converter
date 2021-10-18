package az.siftoshka.junkyconverter.presentation.screens.junksetting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.junkyconverter.data.repository.SharedPrefManager
import az.siftoshka.junkyconverter.domain.model.Junk
import az.siftoshka.junkyconverter.domain.usecases.JunkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The [ViewModel] of the [JunkSettingsScreen].
 */
@HiltViewModel
class JunkSettingsViewModel @Inject constructor(
    private val junkUseCases: JunkUseCases,
    private val prefs: SharedPrefManager
) : ViewModel() {

    private val _junksState = mutableStateOf(JunkListState())
    val junksState: State<JunkListState> = _junksState

    private var getJunksJob: Job? = null

    init {
        getAllJunks()
    }

    private fun getAllJunks() {
        getJunksJob?.cancel()
        getJunksJob = junkUseCases.getJunks().onEach { junks ->
            _junksState.value = junksState.value.copy(junks = junks)
        }.launchIn(viewModelScope)
    }

    fun updateJunk(junk: Junk, value: Float?) {
        if (value != null && value > 0) {
            viewModelScope.launch {
                junkUseCases.updateJunk(junk.copy(value = value))
                prefs.selectJunk(junk.id)
            }
        }
    }
}

data class JunkListState(
    val isLoading: Boolean = false,
    val junks: List<Junk>? = null,
    val error: String = String()
)