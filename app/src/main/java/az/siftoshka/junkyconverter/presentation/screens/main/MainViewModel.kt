package az.siftoshka.junkyconverter.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.junkyconverter.domain.model.Junk
import az.siftoshka.junkyconverter.domain.repository.LocalRepository
import az.siftoshka.junkyconverter.domain.usecase.JunkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * The [ViewModel] of the [MainScreen].
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val junkUseCases: JunkUseCases,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val moneyBuilder = StringBuilder()
    private var maxLength: Int = 3
    private val zero = "0"

    private val _junkState = mutableStateOf(SelectedJunkState())
    val junkState: State<SelectedJunkState> = _junkState

    private val _junksState = mutableStateOf(JunkListState())
    val junksState: State<JunkListState> = _junksState

    var yourMoney: String by mutableStateOf(zero)
        private set

    var junkMoney: String by mutableStateOf(zero)
        private set

    private var getJunksJob: Job? = null
    var selectedJunk: Junk? = null

    init {
        getAllJunks()
    }

    private fun getAllJunks() {
        getJunksJob?.cancel()
        getJunksJob = junkUseCases.getJunks().onEach { junks ->
            _junksState.value = junksState.value.copy(junks = junks)
            junks.find { it.id == localRepository.getSelectedJunk() }?.let { setJunk(it) }
        }.launchIn(viewModelScope)
    }

    fun computeYourMoney(value: String) {
        when (value) {
            "❮" -> {
                if (moneyBuilder.length > 1) {
                    if (moneyBuilder[moneyBuilder.length - 1] == '.') {
                        maxLength = 4
                    }
                }
                if (moneyBuilder.isNotEmpty()) {
                    moneyBuilder.deleteCharAt(moneyBuilder.lastIndex)
                    yourMoney = moneyBuilder.toString()
                }
                if (moneyBuilder.isEmpty()) {
                    moneyBuilder.clear()
                    yourMoney = zero
                }
            }
            "." -> {
                if (moneyBuilder.isNotEmpty() && yourMoney != zero) {
                    moneyBuilder.append(value)
                    yourMoney = moneyBuilder.toString()
                    maxLength = moneyBuilder.length + 1
                }
            }
            else -> {
                if (moneyBuilder.length <= maxLength) {
                    moneyBuilder.append(value)
                    yourMoney = moneyBuilder.toString()
                }
            }
        }
        computeJunkMoney()
    }

    private fun computeJunkMoney() {
        junkMoney = if (yourMoney != zero) {
            yourMoney.toFloat().div(_junkState.value.junk?.value ?: 1f).toString()
        } else zero
    }

    fun setJunk(junk: Junk) {
        localRepository.selectJunk(junk.id)
        _junkState.value = junkState.value.copy(junk = junk)
        selectedJunk = junk
        clearData()
    }

    fun clearData() {
        maxLength = 4
        moneyBuilder.clear()
        yourMoney = zero
        junkMoney = zero
    }
}

data class SelectedJunkState(
    val junk: Junk? = null
)

data class JunkListState(
    val isLoading: Boolean = false,
    val junks: List<Junk>? = null,
    val error: String = String()
)