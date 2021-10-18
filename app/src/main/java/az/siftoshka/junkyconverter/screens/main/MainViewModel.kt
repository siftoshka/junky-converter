package az.siftoshka.junkyconverter.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.junkyconverter.data.model.Junk
import az.siftoshka.junkyconverter.usecases.GetJunkListUseCase
import az.siftoshka.junkyconverter.usecases.GetJunkUseCase
import az.siftoshka.junkyconverter.usecases.SetJunkUseCase
import az.siftoshka.junkyconverter.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * The [ViewModel] of the [MainScreen].
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getJunkUseCase: GetJunkUseCase,
    private val getJunkListUseCase: GetJunkListUseCase,
    private val setJunkUseCase: SetJunkUseCase
) : ViewModel() {

    private val moneyBuilder = StringBuilder()
    private var maxLength: Int = 3
    private val zero = "0"

    private val _junkState = mutableStateOf(SelectedJunkState())
    val junkState: MutableState<SelectedJunkState> = _junkState

    private val _junksState = mutableStateOf(JunkListState())
    val junksState: MutableState<JunkListState> = _junksState

    var yourMoney: String by mutableStateOf(zero)
        private set

    var junkMoney: String by mutableStateOf(zero)
        private set

    init {
        getSelectedJunk()
        getAllJunks()
    }

    private fun getSelectedJunk() {
        getJunkUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _junkState.value = SelectedJunkState(junk = result.data)
                }
                is Resource.Error -> {
                    _junkState.value = SelectedJunkState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _junkState.value = SelectedJunkState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
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
            yourMoney.toFloat().times(_junkState.value.junk?.value ?: 1f).toString()
        } else zero
    }

    fun setJunk(junk: Junk) {
        setJunkUseCase(junk.id)
        _junkState.value = SelectedJunkState(junk = junk)
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
    val isLoading: Boolean = false,
    val junk: Junk? = null,
    val error: String = String()
)

data class JunkListState(
    val isLoading: Boolean = false,
    val junks: List<Junk>? = null,
    val error: String = String()
)